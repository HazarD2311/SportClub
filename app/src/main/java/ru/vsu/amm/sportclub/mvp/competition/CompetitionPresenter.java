package ru.vsu.amm.sportclub.mvp.competition;


import java.util.List;

import ru.vsu.amm.sportclub.data.Competition;
import ru.vsu.amm.sportclub.data.Sportsman;
import ru.vsu.amm.sportclub.mvp.sportsman.SportsmanModel;
import ru.vsu.amm.sportclub.mvp.sportsman.SportsmanPresenter;

public class CompetitionPresenter {

    private CompetitionView view;
    private CompetitionModel model;

    public CompetitionPresenter() {
    }

    public CompetitionPresenter(CompetitionModel model) {
        this.model = model;
    }

    public void attachView(CompetitionView view) {
        this.view = view;
    }

    public void detachView(CompetitionView view) {
        this.view = null;
    }

    public void addNewCompetition(Competition competition, Sportsman[] sportsmenInCompetition) {
        model.addNewCompetition(competition, sportsmenInCompetition);
    }

    public void deleteCompetition(Long id, int position) {
        model.deleteCompetition(id);
        model.removeFromList(position);
    }

    public Boolean isCompetitionListNull() {
        return model.isCompetitionListNull();
    }

    public void loadCompetition() {
        model.readFromDB();
    }

    public List<Competition> getCompetitionList() {
        return model.getCompetitionList();
    }

    public Competition getCompetition(Long id) {
        return model.getCompetition(id);
    }

    public List<Sportsman> getSportsmenWithoutInjury() {
        SportsmanPresenter sportsmanPresenter = new SportsmanPresenter(new SportsmanModel());
        return sportsmanPresenter.getSportsmenWithoutInjury();
    }
}
