package com.example.projetcinemaapi.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "acteur")
public class Acteur {
    @Id
    @Column(name = "NoAct", nullable = false)
    private Integer id;

    @Column(name = "NomAct", nullable = false, length = 20)
    private String nomAct;

    @Column(name = "PrenAct", length = 20)
    private String prenAct;

    @Column(name = "DateNaiss")
    private LocalDate dateNaiss;

    @Column(name = "DateDeces")
    private LocalDate dateDeces;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomAct() {
        return nomAct;
    }

    public void setNomAct(String nomAct) {
        this.nomAct = nomAct;
    }

    public String getPrenAct() {
        return prenAct;
    }

    public void setPrenAct(String prenAct) {
        this.prenAct = prenAct;
    }

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public LocalDate getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(LocalDate dateDeces) {
        this.dateDeces = dateDeces;
    }

}