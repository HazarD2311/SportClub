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

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.ui.activity.CoachActivity;
import ru.vsu.amm.sportclub.ui.adapter.CoachRecycleAdapter;
import ru.vsu.amm.sportclub.db.models.Coach;


public class CoachFragment extends Fragment {

    private static final int LAYOUT = R.layout.coaches_fragment;

    private View view;
    private List<Coach> coachList;
    private RecyclerView recyclerView;
    private CoachRecycleAdapter recyclerAdapter;
    private FloatingActionButton btnAddCoach;


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

        btnAddCoach = (FloatingActionButton) view.findViewById(R.id.btn_add_coach);
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
        Intent intent = new Intent(getActivity(), CoachActivity.class);
        startActivity(intent);
    }

    private void initRecycleView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.coach_recycle);
        //создаем адаптер
        recyclerAdapter = new CoachRecycleAdapter(coachList,
                new CoachRecycleAdapter.OnItemLongClickListener() {
                    @Override
                    public void longClick(View v, Coach coach, int position) {
                        showPopupMenu(v, coach.getId(), position);
                    }
                });
        //создаем и подключаем LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //прикрепляем адаптер к RecycleView
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void showPopupMenu(View v, final Long id, final int position) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v, Gravity.RIGHT);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popup_edit:
                        //вызвать диалоговое окно с переименованием
                        showEditActivity(id);
                        return true;
                    case R.id.popup_delete:
                        //удалить из списка элемент
                        deleteCoach(id, position);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.inflate(R.menu.recycler_popup);
        popupMenu.show();
    }

    private void showEditActivity(Long id) {
        Intent intent = new Intent(getActivity(), CoachActivity.class);
        intent.putExtra("COACH_EDIT_ID", id);
        startActivity(intent);
    }

    private void deleteCoach(Long id, int position) {
        //удаляем из БД
        Coach coach = Coach.findById(Coach.class, id);
        coach.delete();

        //удаляем из списка и оповещаем Recycler
        coachList.remove(position);
        recyclerAdapter.notifyItemRemoved(position);

    }

    private void readFromDB() {
        coachList = Coach.listAll(Coach.class);
    }

}
