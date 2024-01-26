package com.example.TestDeploye.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TestDeploye.DAO.EtudiantDAO;
import com.example.TestDeploye.connexion.Connexion;
import com.example.TestDeploye.model.Etudiant;

import java.sql.*;
/**
 * EtudiantService
 */
@Service
public class EtudiantService {

    private Connexion connex;
    private EtudiantDAO voitureDAO;

    public EtudiantService() {
        voitureDAO = new EtudiantDAO();
        connex = new Connexion();
    }
    public List<Etudiant> findall() throws Exception {
        Connection connection = null;
        List<Etudiant> all_voitures = new ArrayList<>();
        try {
            connection = connex.getConnection();
            all_voitures = voitureDAO.findAll();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return all_voitures;
    }
    public Etudiant findone(int id) throws Exception {
        Connection connection = null;
        Etudiant one_voiture = new Etudiant();
        try {
            connection = connex.getConnection();
            one_voiture = voitureDAO.findOne(id);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return one_voiture;
    }
    //new voiture
    public void create(Etudiant voiture) throws Exception {
        Connection connection = null;
        try {
            connection = connex.getConnection();
            voitureDAO.create(connection, voiture);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }

    public void update(Etudiant voiture,int id) throws Exception {
        Connection connection = null;
        try {
            connection = connex.getConnection();
            voitureDAO.update(voiture,id);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
    public void delete(int id) throws Exception {
        Connection connection = null;
        try {
            connection = connex.getConnection();
            voitureDAO.delete(id);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
}