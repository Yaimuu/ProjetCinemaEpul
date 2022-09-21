package com.example.projetcinemaapi.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "realisateur")
public class Realisateur {
    @Id
    @Column(name = "NoRea", nullable = false)
    private Integer id;

    @Column(name = "NomRea", nullable = false, length = 20)
    private String nomRea;

    @Column(name = "PrenRea", nullable = false, length = 20)
    private String prenRea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomRea() {
        return nomRea;
    }

    public void setNomRea(String nomRea) {
        this.nomRea = nomRea;
    }

    public String getPrenRea() {
        return prenRea;
    }

    public void setPrenRea(String prenRea) {
        this.prenRea = prenRea;
    }

}