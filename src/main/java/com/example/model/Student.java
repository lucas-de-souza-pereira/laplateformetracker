
package com.example.model;

public class Student {
    private String prenom;
    private String nom;
    private int age;
    private String classe;
    private double moyenne;

    public Student(String prenom, String nom, int age, String classe, double moyenne) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.classe = classe;
        this.moyenne = moyenne;
    }   

    // temporaire à configurer avec la DDB
    public String getPrenom() { return prenom; }
    public String getNom() { return nom; }
    public int getAge() { return age; }
    public String getClasse() { return classe; }
    public double getMoyenne() { return moyenne; }
}
