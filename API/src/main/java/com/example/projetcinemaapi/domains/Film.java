package com.example.projetcinemaapi.domains;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "NoFilm", nullable = false)
    private Integer id;

    @Column(name = "Titre", nullable = false, length = 30)
    private String titre;

    @Column(name = "Duree", nullable = false)
    private Integer duree;

    @Column(name = "DateSortie", nullable = false)
    private LocalDate dateSortie;

    @Column(name = "Budget", nullable = false)
    private Integer budget;

    @Column(name = "MontantRecette", nullable = false)
    private Integer montantRecette;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NoRea", nullable = false)
    private Realisateur noRea;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodeCat", nullable = false)
    private Categorie codeCat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getMontantRecette() {
        return montantRecette;
    }

    public void setMontantRecette(Integer montantRecette) {
        this.montantRecette = montantRecette;
    }

    public Realisateur getNoRea() {
        return noRea;
    }

    public void setNoRea(Realisateur noRea) {
        this.noRea = noRea;
    }

    public Categorie getCodeCat() {
        return codeCat;
    }

    public void setCodeCat(Categorie codeCat) {
        this.codeCat = codeCat;
    }

}