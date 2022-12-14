package com.example.projetcinemaapi.domains;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personnage")
@Getter
@ToString
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

    @Setter
    @Column(name = "NomPers", nullable = false, length = 30)
    private String nomPers;
}