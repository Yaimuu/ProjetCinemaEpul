package com.example.projetcinemaapi.domains;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "realisateur", schema = "cinema", catalog = "")
public class RealisateurEntity implements CinemaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NoRea")
    private int noRea;
    @Basic
    @Column(name = "NomRea")
    private String nomRea;
    @Basic
    @Column(name = "PrenRea")
    private String prenRea;

    public int getNoRea() {
        return noRea;
    }

    public void setNoRea(int noRea) {
        this.noRea = noRea;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealisateurEntity that = (RealisateurEntity) o;
        return noRea == that.noRea && Objects.equals(nomRea, that.nomRea) && Objects.equals(prenRea, that.prenRea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noRea, nomRea, prenRea);
    }
}
