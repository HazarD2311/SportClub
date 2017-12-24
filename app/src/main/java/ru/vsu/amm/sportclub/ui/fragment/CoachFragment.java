package ru.vsu.amm.sportclub.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.ui.activity.AddCoachActivity;
import ru.vsu.amm.sportclub.ui.adapter.CoachRecycleAdapter;
import ru.vsu.amm.sportclub.db.models.Coach;


public class CoachFragment extends Fragment {

    private static final int LAYOUT = R.layout.coaches_fragment;

    private View view;
    private List<Coach> coachList;
    private RecyclerView recyclerView;
    private Button btnAddCoach;


    public static CoachFragment getInstance() {
        Bundle args = new Bundle();
        CoachFragment fragment = new CoachFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        btnAddCoach = (Button) view.findViewById(R.id.btn_add_coach);
        btnAddCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddActivity();
            }
        });

        readFromDB();
        initRecycleView();

        return view;
    }

    private void openAddActivity() {
        Intent intent = new Intent(getActivity(), AddCoachActivity.class);
        startActivity(intent);

    }

    private void initRecycleView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.coach_recycle);
        //создаем адаптер
        CoachRecycleAdapter adapter = new CoachRecycleAdapter(coachList);
        //создаем и подключаем LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //прикрепляем адаптер к RecycleView
        recyclerView.setAdapter(adapter);
    }


    private void readFromDB() {
        coachList = Coach.listAll(Coach.class);
    }


    //тестовый метод по заполнению RecycleView
    private List<Coach> getTestList() {
        List<Coach> list = new ArrayList<>();
        Coach coach = new Coach("Викторов", "Виктор", 40, "муж.", "Теннис", "КМС", 4);
        list.add(coach);
        coach = new Coach("Свиридова", "Елена", 25, "жен.", "Воллейбол", "Первый взр.", 3);
        list.add(coach);
        return list;
    }
}
