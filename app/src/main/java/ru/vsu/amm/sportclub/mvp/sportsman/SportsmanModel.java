package ru.vsu.amm.sportclub.mvp.sportsman;

import java.util.List;

import ru.vsu.amm.sportclub.data.Sportsman;

public class SportsmanModel {

    private List<Sportsman> sportsmanList;

    public SportsmanModel() {

    }

    public SportsmanModel(List<Sportsman> sportsmanList) {
        this.sportsmanList = sportsmanList;
    }

    public void addNewSportsman(Sportsman sportsman) {
        sportsman.save();
    }

    public void updateSportsman(Long id, Sportsman newSportsman) {
        Sportsman sportsman = Sportsman.findById(Sportsman.class, id);
        sportsman.setSurname(newSportsman.getSurname());
        sportsman.setName(newSportsman.getName());
        sportsman.setAge(newSportsman.getAge());
        sportsman.setGender(newSportsman.getGender());
        sportsman.setKindOfSport(newSportsman.getKindOfSport());
        sportsman.setQualification(newSportsman.getQualification());
        sportsman.setRating(newSportsman.getRating());
        sportsman.setCoach(newSportsman.getCoach());
        sportsman.setInjury(newSportsman.getInjury());
        sportsman.save();
    }

    public void deleteSportsman(Long id) {
        Sportsman sportsman = Sportsman.findById(Sportsman.class, id);
        sportsman.delete();
    }

    public void removeFromList(int position) {
        sportsmanList.remove(position);
    }

    public void readFromDB() {
        sportsmanList = Sportsman.listAll(Sportsman.class);
    }

    public Sportsman getSportsman(Long id) {
        return Sportsman.findById(Sportsman.class, id);
    }

    public Boolean isSportsmanListNull() {
        return sportsmanList == null;
    }

    public List<Sportsman> getSportsmanList() {
        return sportsmanList;
    }

    public List<Sportsman> getCandidates(String kindOfSport) {
        List<Sportsman> sportsmen = Sportsman.find(Sportsman.class, "injury = ? and kind_of_sport = ?", "нет", kindOfSport);
        return sportsmen;
    }
}
