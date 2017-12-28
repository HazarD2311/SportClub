package ru.vsu.amm.sportclub.db.models;

import com.orm.SugarRecord;

/**
 * Данная таблица реализовавыет связь многие ко многим
 * между Sportsman и Competition (спортменом и соревнованиями)
 */

public class Competitors extends SugarRecord {

    private Competition competition;
    private Sportsman sportsman;

    public Competitors() {

    }

    public Competitors(Competition competition, Sportsman sportsman) {
        this.competition = competition;
        this.sportsman = sportsman;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Sportsman getSportsman() {
        return sportsman;
    }

    public void setSportsman(Sportsman sportsman) {
        this.sportsman = sportsman;
    }
}
