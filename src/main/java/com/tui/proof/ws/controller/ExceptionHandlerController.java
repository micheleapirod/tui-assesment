package com.tui.proof.ws.controller;

import com.tui.proof.dto.ExceptionDto;
import com.tui.proof.dto.TuiOrderDto;
import com.tui.proof.exception.SchemaException;
import com.tui.proof.exception.TuiUserException;
import org.apache.commons.collections.CollectionUtils;
import org.everit.json.schema.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * @param exception
     * @return
     */
    @ExceptionHandler(value = { SchemaException.class })
    public ResponseEntity<?> handleException(SchemaException exception) {
        ValidationException  validationException = exception.getValidationException();
        List<String> messages = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(validationException.getCausingExceptions())) {
            for (ValidationException causingException : validationException.getCausingExceptions()) {
                messages.add(causingException.getMessage());
            }
        } else {
            messages.add(validationException.getMessage());
        }
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessages(messages);
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TuiUserException.class})
    public ResponseEntity<?> handleException(TuiUserException exception) {
        TuiOrderDto tuiOrderDto = TuiOrderDto.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(tuiOrderDto, HttpStatus.UNAUTHORIZED);
    }
}
