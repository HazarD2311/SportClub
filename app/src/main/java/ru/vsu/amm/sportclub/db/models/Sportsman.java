package ru.vsu.amm.sportclub.db.models;


public class Sportsman {

    private int id;
    private String surname;
    private String name;
    private int age;
    private String gender;
    private String kindOfSport;
    private String qualification;
    private int rating;
    private String injury;
    //связь один ко многим с таблицей Coach
    private Coach coach;

    public Sportsman() {

    }

    public Sportsman(String surname, String name, int age, String gender, String kindOfSport, String qualification, int rating, String injury) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getInjury() {
        return injury;
    }

    public void setInjury(String injury) {
        this.injury = injury;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}

