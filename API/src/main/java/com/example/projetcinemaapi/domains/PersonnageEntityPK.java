package com.example.projetcinemaapi.domains;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PersonnageEntityPK implements Serializable {
    @Column(name = "NoFilm")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noFilm;
    @Column(name = "NoAct")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noAct;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonnageEntityPK that = (PersonnageEntityPK) o;
        return noFilm == that.noFilm && noAct == that.noAct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noFilm, noAct);
    }
}
