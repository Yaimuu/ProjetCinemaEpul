package com.example.projetcinemaapi.domains;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "film", schema = "cinema", catalog = "")
public class FilmEntity implements CinemaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NoFilm")
    private int noFilm;
    @Basic
    @Column(name = "Titre")
    private String titre;
    @Basic
    @Column(name = "Duree")
    private int duree;
    @Basic
    @Column(name = "DateSortie")
    private Date dateSortie;
    @Basic
    @Column(name = "Budget")
    private int budget;
    @Basic
    @Column(name = "MontantRecette")
    private int montantRecette;
    @Basic
    @Column(name = "NoRea")
    private int noRea;
    @Basic
    @Column(name = "CodeCat")
    private String codeCat;

    public int getNoFilm() {
        return noFilm;
    }

    public void setNoFilm(int noFilm) {
        this.noFilm = noFilm;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getMontantRecette() {
        return montantRecette;
    }

    public void setMontantRecette(int montantRecette) {
        this.montantRecette = montantRecette;
    }

    public int getNoRea() {
        return noRea;
    }

    public void setNoRea(int noRea) {
        this.noRea = noRea;
    }

    public String getCodeCat() {
        return codeCat;
    }

    public void setCodeCat(String codeCat) {
        this.codeCat = codeCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return noFilm == that.noFilm && duree == that.duree && budget == that.budget && montantRecette == that.montantRecette && noRea == that.noRea && Objects.equals(titre, that.titre) && Objects.equals(dateSortie, that.dateSortie) && Objects.equals(codeCat, that.codeCat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noFilm, titre, duree, dateSortie, budget, montantRecette, noRea, codeCat);
    }
}
