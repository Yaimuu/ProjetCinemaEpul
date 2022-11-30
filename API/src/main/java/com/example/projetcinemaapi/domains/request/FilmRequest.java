package com.example.projetcinemaapi.domains.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class FilmRequest {
    private String titre;
    private Integer duree;
    private LocalDate dateSortie;
    private Integer budget;
    private Integer montantRecette;
    private Integer noRea;
    private String codeCat;
}
