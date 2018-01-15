package ru.vsu.amm.sportclub.mvp.coach;

import java.util.List;

import ru.vsu.amm.sportclub.data.Coach;
import ru.vsu.amm.sportclub.data.Sportsman;

public class CoachPresenter {

    private CoachView view;
    private CoachModel model;

    public CoachPresenter() {
    }

    public CoachPresenter(CoachModel model) {
        this.model = model;
    }

    public void attachView(CoachView view) {
        this.view = view;
    }

    public void detachView(CoachView view) {
        this.view = null;
    }

    public void addNewCoach(Coach coach) {
        model.addNewCoach(coach);
    }

    public void updateCoach(Long id, Coach newCoach) {
        model.updateCoach(id, newCoach);
    }

    public void deleteCoach(Long id, int position) {
        model.deleteCoach(id);
        model.removeFromList(position);
    }

    public void addPoints(Coach coach) {
        coach.addPoint();
        coach.save();
    }

    public Boolean isCoachListNull() {
        return model.isCoachListNull();
    }

    public void loadCoaches() {
        model.readFromDB();
    }

    public List<Coach> getCoaches() {
        return model.getCoachList();
    }

    public Coach getCoach(Long id) {
        return model.getCoach(id);
    }


}
