package com.example.projetcinemaapi.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorie")
@Getter
@Setter
@ToString
public class Categorie {
    @Id
    @Column(name = "CodeCat", nullable = false, length = 2)
    private String id;

    @Column(name = "LibelleCat", nullable = false, length = 20)
    private String libelleCat;

    @Column(name = "image", nullable = false, length = 50)
    private String image;
}