package com.example.projetcinemaapi.domains;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "personnage", schema = "cinema", catalog = "")
@IdClass(PersonnageEntityPK.class)
public class PersonnageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NoFilm")
    private int noFilm;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NoAct")
    private int noAct;
    @Basic
    @Column(name = "NomPers")
    private String nomPers;

    public int getNoFilm() {
        return noFilm;
    }

    public void setNoFilm(int noFilm) {
        this.noFilm = noFilm;
    }

    public int getNoAct() {
        return noAct;
    }

    public void setNoAct(int noAct) {
        this.noAct = noAct;
    }

    public String getNomPers() {
        return nomPers;
    }

    public void setNomPers(String nomPers) {
        this.nomPers = nomPers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonnageEntity that = (PersonnageEntity) o;
        return noFilm == that.noFilm && noAct == that.noAct && Objects.equals(nomPers, that.nomPers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noFilm, noAct, nomPers);
    }
}
