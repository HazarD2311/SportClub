package ru.vsu.amm.sportclub.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.DateTimePatternGenerator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.db.models.Competition;
import ru.vsu.amm.sportclub.db.models.Competitors;
import ru.vsu.amm.sportclub.db.models.Location;
import ru.vsu.amm.sportclub.db.models.Sportsman;

/**
 * Activity для заполнения данных при добавление/изменении Competition
 */

public class CompetitionEditActivity extends AppCompatActivity {

    private Button btnExceptAdd, btnFillFields;
    private EditText edName, edKindOfSport, edLocationName, edLocationAddress, edDate;
    private ListView chooseSportsmen;
    //выбранные спортсмены
    private Sportsman[] sportsmenInCompetition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_edit);

        init();
        useDatePicker();
        //список с кандитатами на турнир
        initListSportsmen();

        btnExceptAdd = (Button) findViewById(R.id.btn_except_add_competition);
        btnExceptAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCompetition();
                backToMainActivity();
            }
        });

    }

    private void addNewCompetition() {
        String name, kindOfSport, locationName, locationAddress, date;
        name = edName.getText().toString();
        kindOfSport = edKindOfSport.getText().toString();
        locationName = edLocationName.getText().toString();
        locationAddress = edLocationAddress.getText().toString();
        date = edDate.getText().toString();

        //добавляем в БД
        Location location = new Location(locationName, locationAddress);
        Competition competition = new Competition(name, kindOfSport, location, date);
        location.save();
        competition.save();

        //Сохранить спортсменов, которые собираются участвовать в данном соревновании
        saveInCompetitors(competition);
    }

    private void saveInCompetitors(Competition competition) {
        for (Sportsman s : sportsmenInCompetition) {
            if (s != null) {
                Competitors competitiors = new Competitors(competition, s);
                competitiors.save();
            }
        }
    }

    private List<Sportsman> getSportsmenFromDB() {
        List<Sportsman> sportsmen = Sportsman.find(Sportsman.class, "injury = ?", "нет");

        return sportsmen;
    }

    private void initListSportsmen() {
        final List<Sportsman> sportsmenCanGo = getSportsmenFromDB();
        chooseSportsmen = (ListView) findViewById(R.id.competition_choose_list);
        ArrayAdapter<Sportsman> adapter = new ArrayAdapter<Sportsman>(this,
                android.R.layout.simple_list_item_multiple_choice, sportsmenCanGo);
        chooseSportsmen.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        chooseSportsmen.setAdapter(adapter);

        //выбираем из здоровых спортсменов отмеченные пользователем
        sportsmenInCompetition = new Sportsman[sportsmenCanGo.size()];
        chooseSportsmen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SparseBooleanArray chosen = ((ListView) adapterView).getCheckedItemPositions();
                if (chosen.valueAt(i)) {
                    sportsmenInCompetition[i] = sportsmenCanGo.get(chosen.keyAt(i));
                } else {
                    sportsmenInCompetition[i] = null;
                }
            }
        });
    }

    private void init() {
        btnExceptAdd = (Button) findViewById(R.id.btn_add_competition);
        btnFillFields = (Button) findViewById(R.id.btn_fill_fields_competition);
        edName = (EditText) findViewById(R.id.edit_competition_name);
        edKindOfSport = (EditText) findViewById(R.id.edit_competition_kind_of_sport);
        edLocationName = (EditText) findViewById(R.id.edit_competition_location_name);
        edLocationAddress = (EditText) findViewById(R.id.edit_competition_location_address);
        edDate = (EditText) findViewById(R.id.edit_competition_date);
    }

    private void useDatePicker() {
        edDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //нынешняя дата
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(CompetitionEditActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                edDate.setText(day + "." + (month + 1) + "." + year);
                            }
                        }, mYear, mMonth, mDay);
                dialog.show();
            }
        });
    }

    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
