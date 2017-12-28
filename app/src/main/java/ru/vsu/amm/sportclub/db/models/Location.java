package ru.vsu.amm.sportclub.db.models;

import com.orm.SugarRecord;


public class Location extends SugarRecord {

    private String name;
    private String address;

    public Location() {

    }

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.address;

    }
}
