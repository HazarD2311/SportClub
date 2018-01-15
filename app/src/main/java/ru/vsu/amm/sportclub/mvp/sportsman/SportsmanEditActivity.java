package ru.vsu.amm.sportclub.mvp.sportsman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ru.vsu.amm.sportclub.Const;
import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.activity.MainActivity;
import ru.vsu.amm.sportclub.mvp.coach.CoachModel;
import ru.vsu.amm.sportclub.mvp.coach.CoachPresenter;
import ru.vsu.amm.sportclub.data.Coach;
import ru.vsu.amm.sportclub.data.Sportsman;

public class SportsmanEditActivity extends AppCompatActivity implements SportsmanView {

    private Button btnExceptAdd, btnFillFields;
    private EditText surname, name, age, gender, kindOfSport, qualification, rating, injury;
    private Spinner coachSpinner;
    //тренер, выбранный в Spinner
    private Coach chosenCoach;

    private SportsmanPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportsman_edit);

        SportsmanModel model = new SportsmanModel();
        presenter = new SportsmanPresenter(model);
        presenter.attachView(this);
        initResources();

        //позиция элемента, на котором была нажата кнопка Изменить
        final Long id;
        id = getIntent().getLongExtra(Const.SPORTSMAN_ID_INTENT, -1);

        //если пришла id, то заполняем поля для удобства
        if (id != -1) {
            fillFieldsFromEdit(id);
        }

        //TODO сделать проверку (вдруг еще не добавлено ни одного тренера)
        //получаем список Тренеров, для последующего подставления в Spinner
        presenter.loadSportsmen();
        initCoachSpinner();


        btnFillFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillFields();
            }
        });
        btnExceptAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidInput()) {
                    if (id != -1) {
                        presenter.updateSportsman(id, getNewSportsman());
                    } else {
                        presenter.addNewSportsman(getNewSportsman());
                    }
                    backToMainActivity();
                } else {
                    Toast.makeText(SportsmanEditActivity.this, "Вы ввели не все данные", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Sportsman getNewSportsman() {
        String strSurname, strName, strGender, strKindOfSport, strQualification, strInjury;
        int strAge, strRating;
        strSurname = surname.getText().toString();
        strName = name.getText().toString();
        strAge = Integer.parseInt(age.getText().toString());
        strGender = gender.getText().toString();
        strKindOfSport = kindOfSport.getText().toString();
        strQualification = qualification.getText().toString();
        strRating = Integer.parseInt(rating.getText().toString());
        strInjury = injury.getText().toString();

        //добавляем в БД
        Sportsman sportsman = new Sportsman(
                strSurname,
                strName,
                strAge,
                strGender,
                strKindOfSport,
                strQualification,
                strRating,
                strInjury,
                chosenCoach);

        return sportsman;
    }

    private void initResources() {
        btnExceptAdd = (Button) findViewById(R.id.btn_except_add_sportsman);
        btnFillFields = (Button) findViewById(R.id.btn_fill_fields_sportsman);
        surname = (EditText) findViewById(R.id.edit_add_sportsman_surname);
        name = (EditText) findViewById(R.id.edit_add_sportsman_name);
        age = (EditText) findViewById(R.id.edit_add_sportsman_age);
        gender = (EditText) findViewById(R.id.edit_add_sportsman_gender);
        kindOfSport = (EditText) findViewById(R.id.edit_add_sportsman_sport);
        qualification = (EditText) findViewById(R.id.edit_add_sportsman_qualification);
        rating = (EditText) findViewById(R.id.edit_add_sportsman_rating);
        injury = (EditText) findViewById(R.id.edit_add_sportsman_injury);
    }

    private void fillFieldsFromEdit(Long id) {
        Sportsman sportsman = presenter.getSportsman(id);
        surname.setText(sportsman.getSurname());
        name.setText(sportsman.getName());
        age.setText(String.valueOf(sportsman.getAge()));
        gender.setText(sportsman.getGender());
        kindOfSport.setText(sportsman.getKindOfSport());
        qualification.setText(sportsman.getQualification());
        rating.setText(String.valueOf(sportsman.getRating()));
        injury.setText(sportsman.getInjury());
    }

    private void fillFields() {
        surname.setText("Иванов");
        name.setText("Иван");
        age.setText("25");
        gender.setText("муж.");
        kindOfSport.setText("Баскетбол");
        qualification.setText("КМС");
        rating.setText("5");
        injury.setText("Ушиб колена");
    }

    private void initCoachSpinner() {
        coachSpinner = (Spinner) findViewById(R.id.sportsman_choose_coach_spinner);
        CoachPresenter cPresenter = new CoachPresenter(new CoachModel());
        cPresenter.loadCoaches();

        ArrayAdapter<Coach> adapter = new ArrayAdapter<Coach>(this, android.R.layout.simple_spinner_item, cPresenter.getCoaches());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coachSpinner.setAdapter(adapter);

        coachSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chosenCoach = (Coach) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean isValidInput() {
        if (surname.getText().toString().equals("")) return false;
        if (name.getText().toString().equals("")) return false;
        if (age.getText().toString().equals("")) return false;
        if (gender.getText().toString().equals("")) return false;
        if (kindOfSport.getText().toString().equals("")) return false;
        if (qualification.getText().toString().equals("")) return false;
        if (rating.getText().toString().equals("")) return false;
        if (injury.getText().toString().equals("")) return false;

        return true;
    }


    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
