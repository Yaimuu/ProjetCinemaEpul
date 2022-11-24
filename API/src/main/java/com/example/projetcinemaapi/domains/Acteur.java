package com.example.projetcinemaapi.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "acteur")
public class Acteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NoAct", nullable = false)
    private Integer id;

    @Setter
    @Column(name = "NomAct", nullable = false, length = 20)
    private String nomAct;

    @Setter
    @Column(name = "PrenAct", length = 20)
    private String prenAct;

    @Setter
    @Column(name = "DateNaiss")
    private LocalDate dateNaiss;

    @Setter
    @Column(name = "DateDeces")
    private LocalDate dateDeces;
}