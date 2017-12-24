package ru.vsu.amm.sportclub.ui.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.ui.adapter.CompetitionRecycleAdapter;
import ru.vsu.amm.sportclub.db.models.Competition;

public class CompetitionFragment extends Fragment {

    private static final int LAYOUT = R.layout.competition_fragment;

    private View view;
    private RecyclerView recyclerView;

    public static CompetitionFragment getInstance() {
        Bundle args = new Bundle();
        CompetitionFragment fragment = new CompetitionFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        initRecycleView(getTestList());

        return view;
    }

    private void initRecycleView(List<Competition> competitionList) {
        recyclerView = (RecyclerView) view.findViewById(R.id.competition_recycler);
        //создаем адаптер
        CompetitionRecycleAdapter adapter = new CompetitionRecycleAdapter(competitionList);
        //создаем и подключаем LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //прикрепляем адаптер к RecycleView
        recyclerView.setAdapter(adapter);
    }

    private List<Competition> getTestList() {
        List<Competition> list = new ArrayList<>();
        Competition competition = new Competition("Чемпионат Мира", "Баскетбол", "США, г. Кливленд", null);
        Date date = new Date();
        competition.setDate(date);
        list.add(competition);
        competition = new Competition("Кубок Москвы", "Хоккей", "Россия, г. Москва", null);
        date = new Date();
        competition.setDate(date);
        list.add(competition);
        return list;
    }
}
