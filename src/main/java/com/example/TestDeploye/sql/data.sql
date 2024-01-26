create database etudiant;
\c etudiant;
create table etudiant(
    idEtudiant serial primary key,
    nom varchar(255),
    etu varchar(255),
    age int
);