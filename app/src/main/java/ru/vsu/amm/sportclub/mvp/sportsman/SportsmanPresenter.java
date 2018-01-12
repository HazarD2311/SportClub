package ru.vsu.amm.sportclub.mvp.sportsman;


import java.util.List;

import ru.vsu.amm.sportclub.data.Competition;
import ru.vsu.amm.sportclub.data.Competitors;
import ru.vsu.amm.sportclub.data.Sportsman;
import ru.vsu.amm.sportclub.mvp.competition.CompetitionModel;
import ru.vsu.amm.sportclub.mvp.competition.CompetitionPresenter;

public class SportsmanPresenter {

    private SportsmanModel model;
    private SportsmanView view;

    public SportsmanPresenter() {
    }

    public SportsmanPresenter(SportsmanModel model) {
        this.model = model;
    }

    public void attachView(SportsmanView view) {
        this.view = view;
    }

    public void detachView(SportsmanView view) {
        this.view = null;
    }

    public Boolean isSportsmanListNull() {
        return model.isSportsmanListNull();
    }

    public void addNewSportsman(Sportsman sportsman) {
        model.addNewSportsman(sportsman);
    }

    public void updateSportsman(Long id, Sportsman sportsman) {
        model.updateSportsman(id, sportsman);
    }

    public void deleteSportsman(Long id, int position) {
        model.deleteSportsman(id);
        model.removeFromList(position);
    }

    public void loadSportsmen() {
        model.readFromDB();
    }

    public List<Competitors> getCompetitions(Long idSportsman) {
        CompetitionPresenter p = new CompetitionPresenter(new CompetitionModel());
        return p.getSportsmenCompetition(idSportsman);
    }

    public Boolean isCompetitor(Long idSportsman) {
        List<Competitors> sportsmenCompetition = getCompetitions(idSportsman);
        return !sportsmenCompetition.isEmpty();
    }

    public List<Sportsman> getSportsmenList() {
        return model.getSportsmanList();
    }

    public Sportsman getSportsman(Long id) {
        return model.getSportsman(id);
    }

    public List<Sportsman> getCandidates(String kindOfSport) {
        return model.getCandidates(kindOfSport);
    }
}
