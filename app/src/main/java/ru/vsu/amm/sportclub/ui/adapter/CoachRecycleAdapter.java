package ru.vsu.amm.sportclub.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.vsu.amm.sportclub.R;
import ru.vsu.amm.sportclub.db.models.Coach;

public class CoachRecycleAdapter extends RecyclerView.Adapter<CoachRecycleAdapter.CoachViewHolder> {

    private List<Coach> coachList;

    public CoachRecycleAdapter(List<Coach> coachList) {
        this.coachList = coachList;
    }

    @Override
    public CoachViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach, parent, false);
        return new CoachViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CoachViewHolder holder, int position) {
        Coach coach = coachList.get(position);
        holder.surname.setText(coach.getSurname());
        holder.name.setText(coach.getName());
        holder.age.setText(String.valueOf(coach.getAge()));
        holder.gender.setText(coach.getGender());
        holder.kind_of_sport.setText(coach.getKindOfSport());
        holder.qualification.setText(coach.getQualification());
        holder.rating.setText(String.valueOf(coach.getRating()));
    }

    @Override
    public int getItemCount() {
        return coachList.size();
    }

    public class CoachViewHolder extends RecyclerView.ViewHolder {
        private TextView surname, name, age, gender, kind_of_sport, qualification, rating;

        public CoachViewHolder(View itemView) {
            super(itemView);
            surname = (TextView) itemView.findViewById(R.id.coach_surname);
            name = (TextView) itemView.findViewById(R.id.coach_name);
            age = (TextView) itemView.findViewById(R.id.coach_age);
            gender = (TextView) itemView.findViewById(R.id.coach_gender);
            kind_of_sport = (TextView) itemView.findViewById(R.id.coach_kind_of_sport);
            qualification = (TextView) itemView.findViewById(R.id.coach_qualification);
            rating = (TextView) itemView.findViewById(R.id.coach_rating);
        }
    }

}
