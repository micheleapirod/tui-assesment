package com.tui.proof.exception;

import lombok.Getter;
import lombok.Setter;
import org.everit.json.schema.ValidationException;

import java.util.List;

@Getter
@Setter
public class SchemaException extends Exception {

    private ValidationException validationException;
    private String message;

    public SchemaException(String message) {
        this.message = message;
    }

    public SchemaException(ValidationException validationException) {
        this.validationException = validationException;
    }

}
