package com.example.projetcinemaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Film Code Already Exists")
public class FilmIdAlreadyUsedException extends RuntimeException{
}
