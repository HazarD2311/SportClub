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
import java.util.List;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.ui.adapter.SportsmanRecycleAdapter;
import ru.vsu.amm.sportclub.db.models.Sportsman;


public class SportsmanFragment extends Fragment {

    private static final int LAYOUT = R.layout.sportsmans_fragment;

    private View view;
    private RecyclerView recyclerView;

    public static SportsmanFragment getInstance() {
        Bundle args = new Bundle();
        SportsmanFragment fragment = new SportsmanFragment();
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

    private void initRecycleView(List<Sportsman> sportsmanList) {
        recyclerView = (RecyclerView) view.findViewById(R.id.sportsman_recycler);
        //создаем адаптер
        SportsmanRecycleAdapter adapter = new SportsmanRecycleAdapter(sportsmanList);
        //создаем и подключаем LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //прикрепляем адаптер к RecycleView
        recyclerView.setAdapter(adapter);
    }

    private List<Sportsman> getTestList() {
        List<Sportsman> list = new ArrayList<>();
        Sportsman sportsman = new Sportsman("Иванов", "Иван", 40, "муж.", "Баскетбол", "КМС", 4, null);
        list.add(sportsman);
        sportsman = new Sportsman("Петров", "Петр", 20, "муж.", "Футбол", "Первый взр.", 3, null);
        list.add(sportsman);
        return list;
    }

}
