package ru.vsu.amm.sportclub.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.amm.sportclub.Const;
import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.data.Competitors;
import ru.vsu.amm.sportclub.data.Sportsman;

public class SportsmenInCompetitionActivity extends AppCompatActivity {

    private ListView listView;
    private List<Sportsman> sportsmenInCompetition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportsmen_in_competition);

        listView = (ListView) findViewById(R.id.list_view_sportsmen_in_competition);
        Long id = getIntent().getLongExtra(Const.COMPETITION_TO_SEE_SPORTSMEN_INTENT, -1);
        getSportsmen(id);

        ArrayAdapter<Sportsman> adapter = new ArrayAdapter<Sportsman>(this,
                android.R.layout.simple_list_item_1, sportsmenInCompetition);
        listView.setAdapter(adapter);
    }

    private void getSportsmen(Long id) {
        List<Competitors> competitors = Competitors.find(Competitors.class, "competition = ?", String.valueOf(id));

        sportsmenInCompetition = new ArrayList<>();
        for (Competitors c : competitors) {
            sportsmenInCompetition.add(c.getSportsman());
        }
    }
}
