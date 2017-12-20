package ru.vsu.amm.sportclub.models;

import java.util.List;

/**
 * Модель для спортмена
 */

public class Sportsman {

    private String surname;
    private String name;
    private int age;
    private String gender;
    private String kindOfSport;
    private String qualification;
    private int rating;
    private List<String> injury;

    public Sportsman() {

    }

    public Sportsman(String surname, String name, int age, String gender, String kindOfSport, String qualification, int rating, List<String> injury) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.kindOfSport = kindOfSport;
        this.qualification = qualification;
        this.rating = rating;
        this.injury = injury;
    }

    @Override
    public String toString() {
        return "Фамилия: " + surname + "\n" +
                "Имя:" + name + "\n" +
                "Возраст:" + age + "\n" +
                "Пол:" + gender + "\n" +
                "Вид спорта:" + kindOfSport + "\n" +
                "Квалификация:" + qualification + "\n" +
                "Рейтинг:" + rating + "\n" +
                "Имя:" + injury.toString() + "\n";
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

    public List<String> getInjury() {
        return injury;
    }

    public String getInjuryToString() {
        return injury.toString();
    }

    public void setInjury(List<String> injury) {
        this.injury = injury;
    }

}

