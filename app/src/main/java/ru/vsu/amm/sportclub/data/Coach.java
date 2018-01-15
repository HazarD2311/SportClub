package ru.vsu.amm.sportclub.data;


import com.orm.SugarRecord;

public class Coach extends SugarRecord {

    private String surname;
    private String name;
    private int age;
    private String gender;
    private String kindOfSport;
    private String qualification;
    private int rating;

    public Coach() {
        this.rating = 0;
    }

    public Coach(String surname, String name, int age, String gender, String kindOfSport, String qualification, int rating) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.kindOfSport = kindOfSport;
        this.qualification = qualification;
        this.rating = rating;
    }

    public void addPoint() {
        this.rating++;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKindOfSport() {
        return kindOfSport;
    }

    public void setKindOfSport(String kindOfSport) {
        this.kindOfSport = kindOfSport;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.getSurname() + " " + this.getName();
    }

}
