package com.example.projetcinemaapi.domains.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class FilmRequest {
    private String titre;
    private Integer duree;
    private LocalDateTime dateSortie;
    private Integer budget;
    private Integer montantRecette;
    private Integer noRea;
    private String codeCat;
}
