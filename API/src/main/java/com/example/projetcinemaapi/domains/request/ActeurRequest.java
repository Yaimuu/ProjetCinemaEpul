package com.example.projetcinemaapi.domains.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ActeurRequest {
    private String nomAct;
    private String prenAct;
    private LocalDate dateNaiss;
    private LocalDate dateDeces;
}
