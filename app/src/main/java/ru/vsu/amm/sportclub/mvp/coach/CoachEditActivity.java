package ru.vsu.amm.sportclub.mvp.coach;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.vsu.amm.sportclub.Const;
import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.activity.MainActivity;
import ru.vsu.amm.sportclub.data.Coach;

public class CoachEditActivity extends AppCompatActivity implements CoachView {

    private Button btnExceptAdd, btnFillFields;
    private EditText surname, name, age, gender, kindOfSport, qualification, rating;

    private CoachPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);

        CoachModel model = new CoachModel();
        presenter = new CoachPresenter(model);
        presenter.attachView(this);
        initResources();

        //позиция элемента, на котором была нажата кнопка Изменить
        final Long id;
        id = getIntent().getLongExtra(Const.COACH_ID_INTENT, -1);

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
                if (isValidInput()) {
                    if (id != -1) {
                        presenter.updateCoach(id, getNewCoach());
                    } else {
                        presenter.addNewCoach(getNewCoach());
                    }
                    backToMainActivity();
                } else {
                    Toast.makeText(CoachEditActivity.this, "Вы ввели не все данные", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initResources() {
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

    private void fillFieldsFromEdit(Long id) {
        Coach coach = presenter.getCoach(id);
        surname.setText(coach.getSurname());
        name.setText(coach.getName());
        age.setText(String.valueOf(coach.getAge()));
        gender.setText(coach.getGender());
        kindOfSport.setText(coach.getKindOfSport());
        qualification.setText(coach.getQualification());
        rating.setText(String.valueOf(coach.getRating()));
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

    private Coach getNewCoach() {
        String strSurname, strName, strGender, strKindOfSport, strQualification;
        int strAge, strRating;
        strSurname = surname.getText().toString();
        strName = name.getText().toString();
        strAge = Integer.parseInt(age.getText().toString());
        strGender = gender.getText().toString();
        strKindOfSport = kindOfSport.getText().toString();
        strQualification = qualification.getText().toString();
        strRating = Integer.parseInt(rating.getText().toString());

        Coach coach = new Coach();
        coach.setSurname(strSurname);
        coach.setName(strName);
        coach.setAge(strAge);
        coach.setGender(strGender);
        coach.setKindOfSport(strKindOfSport);
        coach.setQualification(strQualification);
        coach.setRating(strRating);

        return coach;
    }

    private boolean isValidInput() {
        if (surname.getText().toString().equals("")) return false;
        if (name.getText().toString().equals("")) return false;
        if (age.getText().toString().equals("")) return false;
        if (gender.getText().toString().equals("")) return false;
        if (kindOfSport.getText().toString().equals("")) return false;
        if (qualification.getText().toString().equals("")) return false;
        if (rating.getText().toString().equals("")) return false;

        return true;
    }

    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
