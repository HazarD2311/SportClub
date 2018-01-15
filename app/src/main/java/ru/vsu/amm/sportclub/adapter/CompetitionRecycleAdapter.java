package ru.vsu.amm.sportclub.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.data.Competition;

public class CompetitionRecycleAdapter extends RecyclerView.Adapter<CompetitionRecycleAdapter.CompetitionViewHolder> {

    private List<Competition> competitionList;
    private CompetitionRecycleAdapter.OnItemLongClickListener longClickListener;
    private CompetitionRecycleAdapter.OnItemClickListener clickListener;

    public CompetitionRecycleAdapter(List<Competition> competitionList,
                                     CompetitionRecycleAdapter.OnItemClickListener clickListener,
                                     CompetitionRecycleAdapter.OnItemLongClickListener longClickListener) {
        this.competitionList = competitionList;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    @Override
    public CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competition, parent, false);
        return new CompetitionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CompetitionViewHolder holder, final int position) {
        final Competition competition = competitionList.get(position);
        holder.name.setText(competition.getName());
        holder.kind_of_sport.setText(competition.getKindOfSport());
        if (competition.getLocation() != null)
            holder.location.setText(competition.getLocation().toString());
        holder.date.setText(competition.getDate());
        if (competition.getComplete())
            holder.itemView.setBackgroundColor(Color.RED);

        if (clickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.click(view, competition);
                }
            });
        }
        if (longClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longClickListener.longClick(view, competition, position);
                    return false;
                }
            });
        }
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

    public interface OnItemClickListener {
        void click(View v, Competition competition);
    }

    public interface OnItemLongClickListener {
        void longClick(View v, Competition competition, int position);
    }

}
