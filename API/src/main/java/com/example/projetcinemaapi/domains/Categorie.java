package com.example.projetcinemaapi.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @Column(name = "CodeCat", nullable = false, length = 2)
    private String id;

    @Column(name = "LibelleCat", nullable = false, length = 20)
    private String libelleCat;

    @Column(name = "image", nullable = false, length = 50)
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelleCat() {
        return libelleCat;
    }

    public void setLibelleCat(String libelleCat) {
        this.libelleCat = libelleCat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}