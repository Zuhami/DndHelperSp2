package app.utils;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import app.security.exceptions.ApiException;
import io.javalin.http.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Purpose: Utility class to read properties from a file
 * Author: Thomas Hartmann
 */
public class PlaceholderUtils {
    public static void main(String[] args) {
        System.out.println(getPropertyValue("db.name", "properties-from-pom.properties")); // TODO: Ret property hvis nødvendigt
    }

    // TODO: Bruges til at hente værdier fra .properties filer
    public static String getPropertyValue(String propName, String resourceName)  {
        try (InputStream is = Utils.class.getClassLoader().getResourceAsStream(resourceName)) {
            Properties prop = new Properties();
            prop.load(is);
            String value = prop.getProperty(propName);
            if (value != null) {
                return value.trim();
            } else {
                throw new ApiException(500, String.format("Property %s not found in %s", propName, resourceName));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ApiException(500, String.format("Could not read property %s. Did you remember to build the project with MAVEN?", propName));
        }
    }

    // TODO: ObjectMapper bruges til at parse JSON
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writer(new DefaultPrettyPrinter());
        return objectMapper;
    }

    // TODO: Bruges til at konvertere fejlbeskeder til JSON-format
    public static String convertToJsonMessage(Context ctx, String property, String message) {
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put(property, message);
        msgMap.put("status", String.valueOf(ctx.status()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(msgMap);
        } catch (Exception e) {
            return "{\"error\": \"Could not convert message to JSON\"}";
        }
    }
}
