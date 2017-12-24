package ru.vsu.amm.sportclub.db.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.vsu.amm.sportclub.Const;

public class Competition {

    private String name;
    private String kind_of_sport;
    private String location;
    private Date date;

    public Competition() {

    }

    public Competition(String name, String kind_of_sport, String location, Date date) {
        this.name = name;
        this.kind_of_sport = kind_of_sport;
        this.location = location;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind_of_sport() {
        return kind_of_sport;
    }

    public void setKind_of_sport(String kind_of_sport) {
        this.kind_of_sport = kind_of_sport;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Const.DATE_FORMAT);
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
