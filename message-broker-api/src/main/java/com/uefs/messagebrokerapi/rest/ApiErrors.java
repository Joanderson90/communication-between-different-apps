package com.uefs.messagebrokerapi.rest;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class ApiErrors {

    private final List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String messageError) {
        this.errors = Collections.singletonList(messageError);
    }
}
