package ru.vsu.amm.sportclub.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.db.models.Coach;

public class AddCoachActivity extends AppCompatActivity {

    private Button btnExceptAdd;
    private EditText surname, name, age, gender, kindOfSport, qualification, rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coach);

        init();
        btnExceptAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnExceptPressed();
                backToMainActivity();
            }
        });
    }

    private void btnExceptPressed() {
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

    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void init() {
        btnExceptAdd = (Button) findViewById(R.id.btn_except_add_coach);
        surname = (EditText) findViewById(R.id.edit_add_coach_surname);
        name = (EditText) findViewById(R.id.edit_add_coach_name);
        age = (EditText) findViewById(R.id.edit_add_coach_age);
        gender = (EditText) findViewById(R.id.edit_add_coach_gender);
        kindOfSport = (EditText) findViewById(R.id.edit_add_coach_sport);
        qualification = (EditText) findViewById(R.id.edit_add_coach_qualification);
        rating = (EditText) findViewById(R.id.edit_add_coach_rating);
    }
}
