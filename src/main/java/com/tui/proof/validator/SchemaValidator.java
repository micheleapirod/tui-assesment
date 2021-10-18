package com.tui.proof.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.proof.exception.SchemaException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

/**
 * @author Michele Apicella
 */
@Service
public class SchemaValidator implements Validator {

    private final static Schema schema = SchemaInitializer.getSchema();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public boolean validate(Object object) throws JsonProcessingException, SchemaException {
        try {
            schema.validate(transformRequest2JsonObject(object));
        } catch (ValidationException e) {
            throw new SchemaException(e);
        }
        return true;
    }

    private JSONObject transformRequest2JsonObject(Object request) throws JsonProcessingException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        return new JSONObject(new JSONTokener(jsonRequest));
    }
}
