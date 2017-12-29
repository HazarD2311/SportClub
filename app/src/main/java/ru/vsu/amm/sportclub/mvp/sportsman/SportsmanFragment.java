package ru.vsu.amm.sportclub.mvp.sportsman;


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
import ru.vsu.amm.sportclub.adapter.SportsmanRecycleAdapter;
import ru.vsu.amm.sportclub.data.Sportsman;

public class SportsmanFragment extends Fragment implements SportsmanView {

    private static final int LAYOUT = R.layout.sportsmans_fragment;

    private View view;
    private RecyclerView recyclerView;
    private SportsmanRecycleAdapter recycleAdapter;
    private FloatingActionButton btnAddSportsman;

    private SportsmanPresenter presenter;

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

        SportsmanModel model = new SportsmanModel();
        presenter = new SportsmanPresenter(model);
        presenter.attachView(this);

        btnAddSportsman = (FloatingActionButton) view.findViewById(R.id.btn_add_sportsman);
        btnAddSportsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddActivity();
            }
        });

        presenter.loadSportsmen();
        if (!presenter.isSportsmanListNull()) {
            initRecycleView();
        }
        return view;
    }

    private void initRecycleView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.sportsman_recycler);
        //создаем адаптер
        recycleAdapter = new SportsmanRecycleAdapter(presenter.getSportsmenList(),
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
                        presenter.deleteSportsman(id, position);
                        deleteSportsmanFromRecycler(position);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.inflate(R.menu.popup);
        popupMenu.show();
    }

    private void deleteSportsmanFromRecycler(int position) {
        recycleAdapter.notifyItemRemoved(position);
    }

    private void showEditActivity(Long id) {
        Intent intent = new Intent(getActivity(), SportsmanEditActivity.class);
        intent.putExtra(Const.SPORTSMAN_ID_INTENT, id);
        startActivity(intent);
    }

    private void openAddActivity() {
        Intent intent = new Intent(getActivity(), SportsmanEditActivity.class);
        startActivity(intent);
    }
}
