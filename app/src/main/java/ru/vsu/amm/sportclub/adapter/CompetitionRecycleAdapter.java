package ru.vsu.amm.sportclub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.models.Competition;

public class CompetitionRecycleAdapter extends RecyclerView.Adapter<CompetitionRecycleAdapter.CompetitionViewHolder> {

    private List<Competition> competitionList;

    public CompetitionRecycleAdapter(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }

    @Override
    public CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competition, parent, false);
        return new CompetitionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CompetitionViewHolder holder, int position) {
        Competition competition = competitionList.get(position);
        holder.name.setText(competition.getName());
        holder.kind_of_sport.setText(competition.getKind_of_sport());
        holder.location.setText(competition.getLocation());
        holder.date.setText(competition.getDateString());
    }

    @Override
    public int getItemCount() {
        return competitionList.size();
    }

    public class CompetitionViewHolder extends RecyclerView.ViewHolder {
        private TextView name, kind_of_sport, location, date;

        public CompetitionViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.competition_name);
            kind_of_sport = (TextView) itemView.findViewById(R.id.competition_kind_of_sport);
            location = (TextView) itemView.findViewById(R.id.competition_location);
            date = (TextView) itemView.findViewById(R.id.competition_date);
        }
    }

}
