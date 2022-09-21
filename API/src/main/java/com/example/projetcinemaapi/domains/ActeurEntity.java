package com.example.projetcinemaapi.domains;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "acteur", schema = "cinema", catalog = "")
public class ActeurEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NoAct")
    private int noAct;
    @Basic
    @Column(name = "NomAct")
    private String nomAct;
    @Basic
    @Column(name = "PrenAct")
    private String prenAct;
    @Basic
    @Column(name = "DateNaiss")
    private Date dateNaiss;
    @Basic
    @Column(name = "DateDeces")
    private Date dateDeces;

    public int getNoAct() {
        return noAct;
    }

    public void setNoAct(int noAct) {
        this.noAct = noAct;
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

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public Date getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(Date dateDeces) {
        this.dateDeces = dateDeces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActeurEntity that = (ActeurEntity) o;
        return noAct == that.noAct && Objects.equals(nomAct, that.nomAct) && Objects.equals(prenAct, that.prenAct) && Objects.equals(dateNaiss, that.dateNaiss) && Objects.equals(dateDeces, that.dateDeces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noAct, nomAct, prenAct, dateNaiss, dateDeces);
    }
}
