package com.example.projetcinemaapi.domains.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonnageRequest {
    private Integer noFilm;
    private Integer noAct;
    private String nomPers;
}
