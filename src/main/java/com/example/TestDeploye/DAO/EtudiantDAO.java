package com.example.TestDeploye.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.TestDeploye.connexion.Connexion;
import com.example.TestDeploye.model.Etudiant;
/**
 * EtudiantDAO
 */
public class EtudiantDAO {

    public EtudiantDAO() {
    }
 // get all voiture
 public List<Etudiant> findAll(Connection con) throws Exception {
    Statement stmt = null;
    List<Etudiant> voitures = new ArrayList<>();
    
    try{
        String query = "SELECT * FROM etudiant";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Affichage de tous les etudiants...");
        if(!rs.isBeforeFirst()){
            return null;
        } else {
            while(rs.next()) {
                int idEtu = rs.getInt("idEtudiant");
                String nom = rs.getString("nom");
                String etu=rs.getString("etu");
                int age = rs.getInt("age");

            
               voitures.add(new Etudiant(idEtu,nom,etu,age));
            }
            return voitures;
        }
    } catch (SQLException e) {
        System.out.println("Error with getting all the etudiant...");
        throw e;
    } finally {
        if(stmt != null) {
            stmt.close();
        }
    }
}

public List<Etudiant> findAll() throws Exception {
    Connexion c = new Connexion();
    Connection con = null;
    List<Etudiant> voitures = new ArrayList<>();
    try{
        con = c.getConnection();
        voitures = findAll(con);
    } catch (SQLException e) {
        throw e;
    } finally {
        if(con != null) {
            con.close();
        }
    }
    return voitures;
}
// get one favorite
public Etudiant findOne(Connection con, int idEtu) throws Exception {
    Statement stmt = null;
    Etudiant one_voiture = new Etudiant();
    try {
        String query = "SELECT * FROM etudiant WHERE idEtudiant="+idEtu;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Afficher un etudiant avec idfavoris="+idEtu);
        if(!rs.isBeforeFirst()) {
            return null;
        } else {
            while(rs.next()) {
                String nom = rs.getString("nom");
                String etu=rs.getString("etu");
                int age = rs.getInt("age");              

               one_voiture= new Etudiant(idEtu, nom, etu, age);
            }
            return one_voiture;
        }
    } catch (SQLException e) {
        System.out.println("Error with getting one etudiant...");
        throw e;
    } finally {
        if(stmt != null) {
            stmt.close();
        }
    }
}

public Etudiant findOne(int idEtu) throws Exception {
    Connexion c = new Connexion();
    Connection con = null;
    Etudiant one_voiture = new Etudiant();
    try{
        con = c.getConnection();
        one_voiture = findOne(con, idEtu);
    } catch (SQLException e) {
        throw e;
    } finally {
        if(con != null) {
            con.close();
        }
    }
    return one_voiture;
}

// crud
public void create(Connection con, Etudiant voiture) throws Exception {
    PreparedStatement pstmt = null;
    try {
        String query = "INSERT INTO etudiant(nom,etu,age) VALUES(?,?,?)";
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, voiture.getNom());
        pstmt.setString(2, voiture.getEtu());
        pstmt.setInt(3, voiture.getAge()); // Assuming Marque has a getId() method
        System.out.println("Saving " + voiture.getNom() + " in the table etudiant");
        System.out.println(query);

        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error while saving " + voiture.getNom()+ " in etudiant");
        throw e;
    } finally {
        if (pstmt != null) {
            pstmt.close();
        }
    }
}

public void create(Etudiant voiture) throws Exception {
    Connexion c = new Connexion();
    Connection con = null;
    try{
        con = c.getConnection();
        con.setAutoCommit(false);
        create(con, voiture);
        con.commit();
    }catch (SQLException e) {
        System.out.println("Error persist with insertion in etu");
        throw e;
    } finally {
        if(con != null) {
            con.close();
        }
    }
}

public void update(Connection con, Etudiant voiture, int idEtu) throws Exception {
    PreparedStatement pstmt = null;
    try {
        String query = "UPDATE etudiant SET nom=?,etu=?,age=?  WHERE idEtudiant=?";
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, voiture.getNom());
        pstmt.setString(2, voiture.getEtu());
        pstmt.setInt(3, voiture.getAge()); // Assuming Marque has a getId() method
        pstmt.setInt(4, idEtu);
        System.out.println("Updating id: " + idEtu + " in the table etu to " + voiture.getNom());
        System.out.println(query);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error while updating " + idEtu + " in ETU to " +voiture.getNom());
        throw e;
    } finally {
        if (pstmt != null) {
            pstmt.close();
        }
    }
}
public void update(Etudiant voiture, int idEtu) throws Exception {
    Connexion c = new Connexion();
    Connection con = null;
    try{
        con = c.getConnection();
        con.setAutoCommit(false);
        update(con, voiture, idEtu);
        con.commit();
    }catch (SQLException e) {
        System.out.println("Error while updating "+idEtu+" to "+voiture+" in etu");
        throw e;
    } finally {
        if(con != null) {
            con.close();
        }
    }
}

public void delete(Connection con,int idEtu) throws Exception {
    Statement stmt = null;
    try{
        String query = "DELETE FROM etudiant WHERE idEtudiant="+ idEtu +"";
        stmt = con.createStatement();
        System.out.println("Deleting id :"+ idEtu + " in the table ETU");
        System.out.println(query);

        stmt.executeUpdate(query);
    }catch (SQLException e) {
        System.out.println("Error while deleting "+ idEtu + " in the table etu");
        throw e;
    } finally {
        if(stmt != null) {
            stmt.close();
        }
    }
}
public void delete(int idEtu) throws Exception {
    Connexion c = new Connexion();
    Connection con = null;
    try{
        con = c.getConnection();
        con.setAutoCommit(false);
        delete(con, idEtu);
        con.commit();
    }catch (SQLException e) {
        System.out.println("Error while deleting"+idEtu+" in etu");
        throw e;
    } finally {
        if(con != null) {
            con.close();
        }
    }
}
    
}