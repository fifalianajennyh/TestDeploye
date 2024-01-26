 package com.example.TestDeploye.model;

 public class Etudiant {
    int idEtu;
    String nom;
    String etu;
    int age;
    public Etudiant() {
    }
    public Etudiant(String nom, String etu, int age) {
        this.nom = nom;
        this.etu = etu;
        this.age = age;
    }
    
    public Etudiant(int idEtu, String nom, String etu, int age) {
        this.idEtu = idEtu;
        this.nom = nom;
        this.etu = etu;
        this.age = age;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEtu() {
        return etu;
    }
    public void setEtu(String etu) {
        this.etu = etu;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}