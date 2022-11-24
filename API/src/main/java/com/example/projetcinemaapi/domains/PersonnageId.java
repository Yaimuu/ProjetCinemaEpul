package com.example.projetcinemaapi.domains;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonnageId implements Serializable {
    private static final long serialVersionUID = 7115333595764153627L;
    @Column(name = "NoFilm", nullable = false)
    private Integer noFilm;

    @Column(name = "NoAct", nullable = false)
    private Integer noAct;

    public Integer getNoFilm() {
        return noFilm;
    }

    public void setNoFilm(Integer noFilm) {
        this.noFilm = noFilm;
    }

    public Integer getNoAct() {
        return noAct;
    }

    public void setNoAct(Integer noAct) {
        this.noAct = noAct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersonnageId entity = (PersonnageId) o;
        return Objects.equals(this.noAct, entity.noAct) &&
                Objects.equals(this.noFilm, entity.noFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noAct, noFilm);
    }

}