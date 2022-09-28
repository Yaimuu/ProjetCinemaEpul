package com.example.projetcinemaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Actor Not Found")
public class ActorNotFoundException extends RuntimeException {
}