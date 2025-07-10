package com.example.model;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String classe;
    private double average;

    public Student(int id, String firstName, String lastName, int age, String classe, double average) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.classe = classe;
        this.average = average;
    }

    public Student(String firstName, String lastName, int age, String classe, double average) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.classe = classe;
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
