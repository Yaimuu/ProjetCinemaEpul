package com.example.projetcinemaapi.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NoFilm", nullable = false)
    private Film noFilm;

    @MapsId("noAct")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NoAct", nullable = false)
    private Acteur noAct;

    @Setter
    @Column(name = "NomPers", nullable = false, length = 30)
    private String nomPers;
}