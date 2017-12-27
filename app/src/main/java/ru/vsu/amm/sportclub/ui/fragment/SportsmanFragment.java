package ru.vsu.amm.sportclub.ui.fragment;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.vsu.amm.sportclub.Const;
import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.ui.activity.SportsmanEditActivity;
import ru.vsu.amm.sportclub.ui.adapter.SportsmanRecycleAdapter;
import ru.vsu.amm.sportclub.db.models.Sportsman;


public class SportsmanFragment extends Fragment {

    private static final int LAYOUT = R.layout.sportsmans_fragment;

    private View view;
    private List<Sportsman> sportsmanList;
    private RecyclerView recyclerView;
    private SportsmanRecycleAdapter recycleAdapter;
    private FloatingActionButton btnAddSportsman;

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

        btnAddSportsman = (FloatingActionButton) view.findViewById(R.id.btn_add_sportsman);
        btnAddSportsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddActivity();
            }
        });


        readFromDB();
        if (!sportsmanList.isEmpty()) {
            initRecycleView();
        }
        return view;
    }

    private void initRecycleView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.sportsman_recycler);
        //создаем адаптер
        recycleAdapter = new SportsmanRecycleAdapter(sportsmanList,
                new SportsmanRecycleAdapter.OnItemLongClickListener() {
                    @Override
                    public void longClick(View v, Sportsman sportsman, int position) {
                        showPopupMenu(v, sportsman.getId(), position);
                    }
                });
        //создаем и подключаем LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //прикрепляем адаптер к RecycleView
        recyclerView.setAdapter(recycleAdapter);
    }

    private void showPopupMenu(View v, final Long id, final int position) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v, Gravity.RIGHT);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popup_edit:
                        showEditActivity(id);
                        return true;
                    case R.id.popup_delete:
                        deleteSportsman(id, position);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.inflate(R.menu.popup);
        popupMenu.show();
    }

    private void showEditActivity(Long id) {
        Intent intent = new Intent(getActivity(), SportsmanEditActivity.class);
        intent.putExtra(Const.SPORTSMAN_ID_INTENT, id);
        startActivity(intent);
    }

    private void deleteSportsman(Long id, int position) {
        //удаляем из БД
        Sportsman sportsman = Sportsman.findById(Sportsman.class, id);
        sportsman.delete();

        //удаляем из списка и оповещаем Recycler
        sportsmanList.remove(position);
        recycleAdapter.notifyItemRemoved(position);

    }

    private void readFromDB() {
        sportsmanList = Sportsman.listAll(Sportsman.class);
    }

    private void openAddActivity() {
        Intent intent = new Intent(getActivity(), SportsmanEditActivity.class);
        startActivity(intent);
    }


}
