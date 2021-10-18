package com.tui.proof.validator;

import com.tui.proof.exception.SchemaException;
import com.tui.proof.exception.TuiException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author Michele Apicella
 */
@Component
public class SchemaInitializer {

    @Value("classpath:schema-validator.json"    )
    private Resource schemaValidatorResource;

    private static Schema schema;

    /**
     *
     */
    @PostConstruct
    public void initialize() throws TuiException, SchemaException {
        schema = SchemaLoader.load(loadSchema(schemaValidatorResource));
    }

    public static Schema getSchema() {
        return schema;
    }

    /**
     * @param resource
     * @return
     */
    private JSONObject loadSchema(Resource resource) throws TuiException, SchemaException {
        try {
            Reader reader = new InputStreamReader(resource.getInputStream());
            String schema = FileCopyUtils.copyToString(reader);
            return new JSONObject(new JSONTokener(schema));
        } catch (IOException e) {
            throw new SchemaException("Error on initialization - missing schema");
        }
    }

}
