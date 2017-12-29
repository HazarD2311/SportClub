package ru.vsu.amm.sportclub.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.data.Sportsman;

public class SportsmanRecycleAdapter extends RecyclerView.Adapter<SportsmanRecycleAdapter.SportsmanViewHolder> {

    private List<Sportsman> sportsmanList;
    private SportsmanRecycleAdapter.OnItemLongClickListener longClick;

    public SportsmanRecycleAdapter(List<Sportsman> sportsmanList,
                                   SportsmanRecycleAdapter.OnItemLongClickListener longClick) {
        this.sportsmanList = sportsmanList;
        this.longClick = longClick;
    }

    @Override
    public SportsmanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sportsman, parent, false);
        return new SportsmanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SportsmanViewHolder holder, final int position) {
        final Sportsman sportsman = sportsmanList.get(position);
        holder.surname.setText(sportsman.getSurname());
        holder.name.setText(sportsman.getName());
        holder.age.setText(String.valueOf(sportsman.getAge()));
        holder.gender.setText(sportsman.getGender());
        holder.kind_of_sport.setText(sportsman.getKindOfSport());
        holder.qualification.setText(sportsman.getQualification());
        holder.rating.setText(String.valueOf(sportsman.getRating()));
        holder.injury.setText(sportsman.getInjury());

        String coachName;
        if (sportsman.getCoach() != null) {
            coachName = sportsman.getCoach().getSurname() + " " + sportsman.getCoach().getName();
        } else {
            coachName = "отсутствует";
        }
        holder.coachName.setText(coachName);


        if (longClick != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longClick.longClick(view, sportsman, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return sportsmanList.size();
    }

    public class SportsmanViewHolder extends RecyclerView.ViewHolder {
        private TextView surname, name, age, gender, kind_of_sport, qualification, rating, coachName, injury;

        public SportsmanViewHolder(View itemView) {
            super(itemView);
            surname = (TextView) itemView.findViewById(R.id.sportsman_surname);
            name = (TextView) itemView.findViewById(R.id.sportsman_name);
            age = (TextView) itemView.findViewById(R.id.sportsman_age);
            gender = (TextView) itemView.findViewById(R.id.sportsman_gender);
            kind_of_sport = (TextView) itemView.findViewById(R.id.sportsman_kind_of_sport);
            qualification = (TextView) itemView.findViewById(R.id.sportsman_qualification);
            rating = (TextView) itemView.findViewById(R.id.sportsman_rating);
            coachName = (TextView) itemView.findViewById(R.id.sportsmans_coach_name);
            injury = (TextView) itemView.findViewById(R.id.sportsman_injury);
        }
    }

    public interface OnItemLongClickListener {
        void longClick(View v, Sportsman sportsman, int position);
    }
}
