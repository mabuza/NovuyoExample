package utils.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PojoUtils {
    private static ObjectMapper objectMapper;

    public static ObjectMapper getMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
        }
        return objectMapper;
    }

    public static String toJson(Object pojo) throws JsonProcessingException {
        return getMapper().writeValueAsString(pojo);
    }

    public static String toJsonQuietly(Object pojo) {
        try {
            return getMapper().writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toPrettyJson(Object pojo) throws JsonProcessingException {
        return getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
    }

    public static <T extends Object> T toPojo(String json, Class<T> t)
            throws IOException {
        return getMapper().readValue(json, t);
    }

    public static <T extends Object> T toPojoQuietly(String json, Class<T> t) {
        try {
            return getMapper().readValue(json, t);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T extends Object> List<T> toPojoList(String json, Class<T[]> t)
            throws IOException {
        return Arrays.asList(getMapper().readValue(json, t));
    }

}
