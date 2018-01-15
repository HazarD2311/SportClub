package ru.vsu.amm.sportclub.data;

import com.orm.SugarRecord;

/**
 * Таблица для учета проводимых соревнований
 */

public class Competition extends SugarRecord {

    private String name;
    private String kindOfSport;
    private Location location;
    private String date;
    //завершено ли соревнование
    private Boolean isComplete;
    private Sportsman winner;

    public Competition() {
        this.isComplete = false;
        this.winner = null;
    }

    public Competition(String name, String kind_of_sport, Location location, String date) {
        this.name = name;
        this.kindOfSport = kind_of_sport;
        this.location = location;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKindOfSport() {
        return kindOfSport;
    }

    public void setKindOfSport(String kindOfSport) {
        this.kindOfSport = kindOfSport;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    /*public String getDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Const.DATE_FORMAT);
        return dateFormat.format(date);
    }*/

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public Sportsman getWinner() {
        return winner;
    }

    public void setWinner(Sportsman winner) {
        this.winner = winner;
    }
}
