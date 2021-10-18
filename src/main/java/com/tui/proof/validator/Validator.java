package com.tui.proof.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tui.proof.exception.SchemaException;


/**
 * @author Michele Apicella
 */
public interface Validator {

    public boolean validate(Object object) throws JsonProcessingException, SchemaException;

}
