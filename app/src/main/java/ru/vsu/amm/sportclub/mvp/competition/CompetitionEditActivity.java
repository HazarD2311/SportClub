package ru.vsu.amm.sportclub.mvp.competition;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Calendar;
import java.util.List;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.activity.MainActivity;
import ru.vsu.amm.sportclub.data.Competition;
import ru.vsu.amm.sportclub.data.Location;
import ru.vsu.amm.sportclub.data.Sportsman;

public class CompetitionEditActivity extends AppCompatActivity implements CompetitionView {

    private Button btnExceptAdd, btnFillFields;
    private EditText edName, edKindOfSport, edLocationName, edLocationAddress, edDate;
    private ListView chooseSportsmen;
    private Sportsman[] sportsmenInCompetition;

    private CompetitionPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_edit);

        presenter = new CompetitionPresenter(new CompetitionModel());
        presenter.attachView(this);

        initResources();
        useDatePicker();
        //список с кандитатами на турнир
        final List<Sportsman> sportsmenCanGo = presenter.getSportsmenWithoutInjury();
        initListSportsmen(sportsmenCanGo);
        getSportsmenInCompetition(sportsmenCanGo);

        btnExceptAdd = (Button) findViewById(R.id.btn_except_add_competition);
        btnExceptAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addNewCompetition(getNewCompetition(), sportsmenInCompetition);
                backToMainActivity();
            }
        });
    }

    private void initResources() {
        btnExceptAdd = (Button) findViewById(R.id.btn_add_competition);
        btnFillFields = (Button) findViewById(R.id.btn_fill_fields_competition);
        edName = (EditText) findViewById(R.id.edit_competition_name);
        edKindOfSport = (EditText) findViewById(R.id.edit_competition_kind_of_sport);
        edLocationName = (EditText) findViewById(R.id.edit_competition_location_name);
        edLocationAddress = (EditText) findViewById(R.id.edit_competition_location_address);
        edDate = (EditText) findViewById(R.id.edit_competition_date);
    }

    private Competition getNewCompetition() {
        String name, kindOfSport, locationName, locationAddress, date;
        name = edName.getText().toString();
        kindOfSport = edKindOfSport.getText().toString();
        locationName = edLocationName.getText().toString();
        locationAddress = edLocationAddress.getText().toString();
        date = edDate.getText().toString();

        Location location = new Location(locationName, locationAddress);
        Competition competition = new Competition(name, kindOfSport, location, date);
        location.save();

        return competition;
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

    private void initListSportsmen(List<Sportsman> sportsmenCanGo) {
        chooseSportsmen = (ListView) findViewById(R.id.competition_choose_list);
        ArrayAdapter<Sportsman> adapter = new ArrayAdapter<Sportsman>(this,
                android.R.layout.simple_list_item_multiple_choice, sportsmenCanGo);
        chooseSportsmen.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        chooseSportsmen.setAdapter(adapter);
    }

    private void getSportsmenInCompetition(final List<Sportsman> sportsmenCanGo) {
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

    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

