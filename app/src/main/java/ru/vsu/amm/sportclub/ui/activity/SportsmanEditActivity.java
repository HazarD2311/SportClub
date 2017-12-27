package ru.vsu.amm.sportclub.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import ru.vsu.amm.sportclub.Const;
import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.db.models.Coach;
import ru.vsu.amm.sportclub.db.models.Sportsman;

/**
 * Заполнение данных при добавлении спортсмена в БД Sportsman
 */

public class SportsmanEditActivity extends AppCompatActivity {

    private Button btnExceptAdd, btnFillFields;
    private EditText surname, name, age, gender, kindOfSport, qualification, rating, injury;
    private Spinner coachSpinner;
    private List<Coach> coachList;
    //выбранный Тренер из списка (Spinner)
    private Coach choosenCoach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportsman_edit);

        initResources();

        //позиция элемента, на котором была нажата кнопка Изменить
        final Long id;
        id = getIntent().getLongExtra(Const.SPORTSMAN_ID_INTENT, -1);

        //если пришла id, то заполняем поля для удобства
        if (id != -1) {
            fillFieldsFromEdit(id);
        }

        //получаем список Тренеров, для последующего подставления в Spinner
        getAllCoaches();
        initSpinner();


        btnFillFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillFields();
            }
        });
        btnExceptAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id != -1) {
                    updateSportsman(id);
                } else {
                    addNewSportsman();
                }
                backToMainActivity();
            }
        });
    }

    private void fillFieldsFromEdit(Long id) {
        Sportsman sportsman = Sportsman.findById(Sportsman.class, id);
        surname.setText(sportsman.getSurname());
        name.setText(sportsman.getName());
        age.setText(String.valueOf(sportsman.getAge()));
        gender.setText(sportsman.getGender());
        kindOfSport.setText(sportsman.getKindOfSport());
        qualification.setText(sportsman.getQualification());
        rating.setText(String.valueOf(sportsman.getRating()));
        injury.setText(sportsman.getInjury());
    }

    private void addNewSportsman() {
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
                choosenCoach);
        try {
            sportsman.save();
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка добавления", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateSportsman(Long id) {
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

        //обновляем БД
        Sportsman sportsman = Sportsman.findById(Sportsman.class, id);
        sportsman.setSurname(strSurname);
        sportsman.setName(strName);
        sportsman.setAge(strAge);
        sportsman.setGender(strGender);
        sportsman.setKindOfSport(strKindOfSport);
        sportsman.setQualification(strQualification);
        sportsman.setRating(strRating);
        sportsman.setCoach(choosenCoach);
        sportsman.setInjury(strInjury);
        sportsman.save();
    }

    private void getAllCoaches() {
        coachList = Coach.listAll(Coach.class);
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

    private void initSpinner() {
        coachSpinner = (Spinner) findViewById(R.id.sportsman_choose_coach_spinner);
        ArrayAdapter<Coach> adapter = new ArrayAdapter<Coach>(this, android.R.layout.simple_spinner_item, coachList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coachSpinner.setAdapter(adapter);

        coachSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choosenCoach = (Coach) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
