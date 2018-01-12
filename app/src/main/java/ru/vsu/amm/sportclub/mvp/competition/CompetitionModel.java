package ru.vsu.amm.sportclub.mvp.competition;

import java.util.List;

import ru.vsu.amm.sportclub.data.Competition;
import ru.vsu.amm.sportclub.data.Competitors;
import ru.vsu.amm.sportclub.data.Sportsman;

public class CompetitionModel {

    private List<Competition> competitionList;

    public CompetitionModel() {
    }

    public CompetitionModel(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }

    public void addNewCompetition(Competition competition, Sportsman[] sportsmanInCompetition) {
        competition.save();

        //Сохранить спортсменов, которые собираются участвовать в данном соревновании
        saveInCompetitors(competition, sportsmanInCompetition);
    }

    //добавление в доп.таблицу реализующая связь многие-ко-многим
    private void saveInCompetitors(Competition competition, Sportsman[] sportsmenInCompetition) {
        for (Sportsman s : sportsmenInCompetition) {
            if (s != null) {
                Competitors competitiors = new Competitors(competition, s);
                competitiors.save();
            }
        }
    }

    public void updateCompetitors(Long idCompetition, Competition competition, List<Sportsman> sportsmanInCompetition) {
        //TODO
    }

    public void deleteCompetition(Long id) {
        Competition competition = Competition.findById(Competition.class, id);

        //удалим все упоминания этого соревнования в SportsmenAndCompetition
        List<Competitors> competitors = Competitors.find(Competitors.class, "competition = ?", String.valueOf(id));
        for (Competitors c : competitors) {
            c.delete();
        }

        //удаляем из собственной таблицы (Competition)
        competition.delete();
    }

    public void removeFromList(int position) {
        competitionList.remove(position);
    }

    public void readFromDB() {
        competitionList = Competition.listAll(Competition.class);
    }

    public List<Competition> getCompetitionList() {
        return this.competitionList;
    }

    public Competition getCompetition(Long id) {
        return Competition.findById(Competition.class, id);
    }

    public Boolean isCompetitionListNull() {
        return competitionList == null;
    }

}