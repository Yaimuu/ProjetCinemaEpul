package com.example.projetcinemaapi.domains;

import javax.persistence.*;

@Entity
@Table(name = "personnage")
public class Personnage {
    @EmbeddedId
    private PersonnageId id;

    @MapsId("noFilm")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NoFilm", nullable = false)
    private Film noFilm;

    @MapsId("noAct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NoAct", nullable = false)
    private Acteur noAct;

    @Column(name = "NomPers", nullable = false, length = 30)
    private String nomPers;

    public PersonnageId getId() {
        return id;
    }

    public void setId(PersonnageId id) {
        this.id = id;
    }

    public Film getNoFilm() {
        return noFilm;
    }

    public void setNoFilm(Film noFilm) {
        this.noFilm = noFilm;
    }

    public Acteur getNoAct() {
        return noAct;
    }

    public void setNoAct(Acteur noAct) {
        this.noAct = noAct;
    }

    public String getNomPers() {
        return nomPers;
    }

    public void setNomPers(String nomPers) {
        this.nomPers = nomPers;
    }

}