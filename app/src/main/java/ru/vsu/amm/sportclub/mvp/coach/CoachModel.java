package ru.vsu.amm.sportclub.mvp.coach;


import java.util.List;

import ru.vsu.amm.sportclub.data.Coach;
import ru.vsu.amm.sportclub.data.Sportsman;

public class CoachModel {

    private List<Coach> coachList;

    public CoachModel() {

    }

    public CoachModel(List<Coach> coachList) {
        this.coachList = coachList;
    }

    public void addNewCoach(Coach coach) {
        coach.save();
    }

    public void updateCoach(Long id, Coach newCoach) {
        Coach coach = Coach.findById(Coach.class, id);
        coach.setSurname(newCoach.getSurname());
        coach.setName(newCoach.getName());
        coach.setAge(newCoach.getAge());
        coach.setGender(newCoach.getGender());
        coach.setKindOfSport(newCoach.getKindOfSport());
        coach.setQualification(newCoach.getQualification());
        coach.setRating(newCoach.getRating());
        coach.save();
    }

    public void deleteCoach(Long id) {
        Coach coach = Coach.findById(Coach.class, id);

        //убрать у спортсменов удаленного тренера
        //ссылку на теперь несуществующего тренера
        deleteCoachFromSportsmen(id);

        //теперь можем смело удалять из БД
        coach.delete();
    }

    public void deleteCoachFromSportsmen(Long id) {
        List<Sportsman> sportsmen = Sportsman.find(Sportsman.class, "coach = ?", String.valueOf(id));

        for (Sportsman sportsman : sportsmen) {
            sportsman.setCoach(null);
        }
    }

    public void removeFromList(int position) {
        coachList.remove(position);
    }

    public void readFromDB() {
        coachList = Coach.listAll(Coach.class);
    }

    public List<Coach>
    getCoachList() {
        return coachList;
    }

    public Coach getCoach(Long id) {
        return Coach.findById(Coach.class, id);
    }

    public Boolean isCoachListNull() {
        return coachList == null;
    }
}

