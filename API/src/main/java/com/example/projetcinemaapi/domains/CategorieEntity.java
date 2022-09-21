package com.example.projetcinemaapi.domains;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categorie", schema = "cinema", catalog = "")
public class CategorieEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CodeCat")
    private String codeCat;
    @Basic
    @Column(name = "LibelleCat")
    private String libelleCat;
    @Basic
    @Column(name = "image")
    private String image;

    public String getCodeCat() {
        return codeCat;
    }

    public void setCodeCat(String codeCat) {
        this.codeCat = codeCat;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieEntity that = (CategorieEntity) o;
        return Objects.equals(codeCat, that.codeCat) && Objects.equals(libelleCat, that.libelleCat) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeCat, libelleCat, image);
    }
}
