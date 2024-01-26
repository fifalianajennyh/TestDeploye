package com.example.TestDeploye.connexion;
import java.sql.*;

public class Connexion {

    public Connection getConnection() throws Exception {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/etudiant";
        Class.forName(driver);
        Connection connect = DriverManager.getConnection(url, "postgres", "root");
        return connect;
    }
}