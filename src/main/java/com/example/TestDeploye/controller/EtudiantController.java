package com.example.TestDeploye.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TestDeploye.model.Etudiant;
import com.example.TestDeploye.service.EtudiantService;

/**
 * EtudiantController
 */
@RestController
@RequestMapping("/api/etudiant")
@CrossOrigin(origins = "*")
public class EtudiantController {

    @Autowired
    private EtudiantService voitureService;

    //find all
    @GetMapping("/all")
    public ResponseEntity<List<Etudiant>> getAllETUs() {
        try {
            List<Etudiant> voitures = voitureService.findall();
            if (voitures != null) {
                return new ResponseEntity<>(voitures, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //find one
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Etudiant> getETUById(@PathVariable int id) {
        try {
            Etudiant voiture = voitureService.findone(id);
            if (voiture != null) {
                return new ResponseEntity<>(voiture, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertion(@RequestBody Etudiant voiture) {
        try {
            voitureService.create(voiture);
            return new ResponseEntity<>("Etu created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Etudiant updated_voiture) {
        try {
            Etudiant existingMarque = voitureService.findone(id);
            if (existingMarque != null) {
                voitureService.update(updated_voiture, id);
                return new ResponseEntity<>("etu name updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("etu not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating etu name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            voitureService.delete(id);
            return new ResponseEntity<>("etu deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}