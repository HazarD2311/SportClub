package ru.vsu.amm.sportclub.mvp.coach;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ru.vsu.amm.sportclub.Const;
import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.adapter.CoachRecycleAdapter;
import ru.vsu.amm.sportclub.data.Coach;

/**
 * Фрагмент отвечает за показ списка имеющихся тренеров
 * и возможность работать с ними
 */

public class CoachFragment extends Fragment implements CoachView {

    private static final int LAYOUT = R.layout.coaches_fragment;

    private View view;
    private RecyclerView recyclerView;
    private CoachRecycleAdapter recyclerAdapter;
    private FloatingActionButton btnAddCoach;

    private CoachPresenter presenter;

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

        CoachModel model = new CoachModel();
        presenter = new CoachPresenter(model);
        presenter.attachView(this);

        btnAddCoach = (FloatingActionButton) view.findViewById(R.id.btn_add_coach);
        btnAddCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddActivity();
            }
        });

        presenter.loadCoaches();
        if (!presenter.isCoachListNull()) {
            initRecycleView();
        }

        return view;
    }

    private void initRecycleView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.coach_recycle);
        //создаем адаптер
        recyclerAdapter = new CoachRecycleAdapter(presenter.getCoaches(),
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
                        showEditActivity(id);
                        return true;
                    case R.id.popup_delete:
                        presenter.deleteCoach(id, position);
                        deleteCoachFromRecycler(position);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.inflate(R.menu.popup);
        popupMenu.show();
    }

    private void deleteCoachFromRecycler(int position) {
        recyclerAdapter.notifyItemRemoved(position);
    }

    private void showEditActivity(Long id) {
        Intent intent = new Intent(getActivity(), CoachEditActivity.class);
        intent.putExtra(Const.COACH_ID_INTENT, id);
        startActivity(intent);
    }

    private void openAddActivity() {
        Intent intent = new Intent(getActivity(), CoachEditActivity.class);
        startActivity(intent);
    }

}
