package com.example.projetcinemaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Film Not Found")
public class FilmNotFoundException extends RuntimeException {

}
