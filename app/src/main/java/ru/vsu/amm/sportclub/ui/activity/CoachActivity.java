package ru.vsu.amm.sportclub.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.db.models.Coach;

/**
 * Активити для заполнения данных при добавлении
 * или измении записи в БД Coach
 */

public class CoachActivity extends AppCompatActivity {

    private Button btnExceptAdd, btnFillFields;
    private EditText surname, name, age, gender, kindOfSport, qualification, rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coach);

        init();

        //позиция элемента, на котором была нажата кнопка Изменить
        final Long id;
        id = getIntent().getLongExtra("COACH_EDIT_ID", -1);

        //если пришла id, то заполняем поля для удобства
        if (id != -1) {
            fillFieldsFromEdit(id);
        }

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
                    updateCoach(id);
                } else {
                    addNewCoach();
                }
                backToMainActivity();
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
    }

    private void fillFieldsFromEdit(Long id) {
        Coach coach = Coach.findById(Coach.class, id);
        surname.setText(coach.getSurname());
        name.setText(coach.getName());
        age.setText(String.valueOf(coach.getAge()));
        gender.setText(coach.getGender());
        kindOfSport.setText(coach.getKindOfSport());
        qualification.setText(coach.getQualification());
        rating.setText(String.valueOf(coach.getRating()));
    }

    private void addNewCoach() {
        String strSurname, strName, strGender, strKindOfSport, strQualification;
        int strAge, strRating;
        strSurname = surname.getText().toString();
        strName = name.getText().toString();
        strAge = Integer.parseInt(age.getText().toString());
        strGender = gender.getText().toString();
        strKindOfSport = kindOfSport.getText().toString();
        strQualification = qualification.getText().toString();
        strRating = Integer.parseInt(rating.getText().toString());

        //добавляем в БД
        Coach coach = new Coach(strSurname, strName, strAge, strGender, strKindOfSport, strQualification, strRating);
        coach.save();
    }

    private void updateCoach(Long id) {
        String strSurname, strName, strGender, strKindOfSport, strQualification;
        int strAge, strRating;
        strSurname = surname.getText().toString();
        strName = name.getText().toString();
        strAge = Integer.parseInt(age.getText().toString());
        strGender = gender.getText().toString();
        strKindOfSport = kindOfSport.getText().toString();
        strQualification = qualification.getText().toString();
        strRating = Integer.parseInt(rating.getText().toString());

        //обновляем БД
        Coach coach = Coach.findById(Coach.class, id);
        coach.setSurname(strSurname);
        coach.setName(strName);
        coach.setAge(strAge);
        coach.setGender(strGender);
        coach.setKindOfSport(strKindOfSport);
        coach.setQualification(strQualification);
        coach.setRating(strRating);
        coach.save();
    }

    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void init() {
        btnExceptAdd = (Button) findViewById(R.id.btn_except_add_coach);
        btnFillFields = (Button) findViewById(R.id.btn_fill_fields_coach);
        surname = (EditText) findViewById(R.id.edit_add_coach_surname);
        name = (EditText) findViewById(R.id.edit_add_coach_name);
        age = (EditText) findViewById(R.id.edit_add_coach_age);
        gender = (EditText) findViewById(R.id.edit_add_coach_gender);
        kindOfSport = (EditText) findViewById(R.id.edit_add_coach_sport);
        qualification = (EditText) findViewById(R.id.edit_add_coach_qualification);
        rating = (EditText) findViewById(R.id.edit_add_coach_rating);
    }
}
