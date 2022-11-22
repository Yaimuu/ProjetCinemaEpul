package com.example.projetcinemaapi.domains;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class PersonnageId implements Serializable {
    private static final long serialVersionUID = 7115333595764153627L;
    @Column(name = "NoFilm", nullable = false)
    private Integer noFilm;

    @Column(name = "NoAct", nullable = false)
    private Integer noAct;

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