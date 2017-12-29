package ru.vsu.amm.sportclub.fragment;

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
import android.widget.Toast;

import java.util.List;

import ru.vsu.amm.sportclub.Const;
import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.data.Competitors;
import ru.vsu.amm.sportclub.activity.CompetitionEditActivity;
import ru.vsu.amm.sportclub.activity.SportsmenInCompetitionActivity;
import ru.vsu.amm.sportclub.adapter.CompetitionRecycleAdapter;
import ru.vsu.amm.sportclub.data.Competition;

public class CompetitionFragment extends Fragment {

    private static final int LAYOUT = R.layout.competition_fragment;

    private View view;
    private RecyclerView recyclerView;
    private CompetitionRecycleAdapter recycleAdapter;
    private List<Competition> competitionList;
    private FloatingActionButton btnAddCompetition;

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

        btnAddCompetition = (FloatingActionButton) view.findViewById(R.id.btn_add_competition);
        btnAddCompetition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddActivity();
            }
        });


        try {
            readFromDB();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Не удалось считать Competition", Toast.LENGTH_SHORT).show();
        }
        try {
            if (!competitionList.isEmpty() || competitionList != null) {
                initRecycleView();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "competitionList Exception", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void initRecycleView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.competition_recycler);
        //создаем адаптер
        recycleAdapter = new CompetitionRecycleAdapter(competitionList,
                new CompetitionRecycleAdapter.OnItemClickListener() {
                    @Override
                    public void click(View v, Competition competition) {
                        Intent intent = new Intent(getActivity(), SportsmenInCompetitionActivity.class);
                        intent.putExtra(Const.COMPETITION_TO_SEE_SPORTSMEN_INTENT, competition.getId());
                        startActivity(intent);
                    }
                },
                new CompetitionRecycleAdapter.OnItemLongClickListener() {
                    @Override
                    public void longClick(View v, Competition competition, int position) {
                        showPopupMenu(v, competition.getId(), position);
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
                        deleteCompetition(id, position);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.inflate(R.menu.popup);
        popupMenu.show();
    }

    private void deleteCompetition(Long id, int position) {
        Competition competition = Competition.findById(Competition.class, id);

        //удалим все упоминания этого соревнования в SportsmenAndCompetition
        List<Competitors> competitors = Competitors.find(Competitors.class, "competition = ?", String.valueOf(id));
        for (Competitors c : competitors) {
            c.delete();
        }

        //удаляем из собственной таблицы (Competition)
        competition.delete();

        //удаляем из списка и оповещаем RecyclerView
        competitionList.remove(position);
        recycleAdapter.notifyItemRemoved(position);
    }

    private void showEditActivity(Long id) {
        Intent intent = new Intent(getActivity(), CompetitionEditActivity.class);
        intent.putExtra(Const.COMPETITION_ID_INTENT, id);
        startActivity(intent);
    }

    private void readFromDB() {
        competitionList = Competition.listAll(Competition.class);
    }

    private void openAddActivity() {
        Intent intent = new Intent(getActivity(), CompetitionEditActivity.class);
        startActivity(intent);
    }

}
