package ru.vsu.amm.sportclub.db.models;

import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.vsu.amm.sportclub.Const;

/**
 * Таблица для учета проводимых соревнований
 */

public class Competition extends SugarRecord {

    private String name;
    private String kindOfSport;
    private Location location;
    private String date;

    public Competition() {

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
}
