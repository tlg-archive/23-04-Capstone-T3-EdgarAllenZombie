package eaz.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonReader {

    public static <T> T readJson(Class<T> clazz, String filename) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = new InputStreamReader(JsonReader.class.getClassLoader().getResourceAsStream(filename))) {
            return gson.fromJson(reader, clazz);
        }
    }

    public static Mansion readMansion() throws IOException {
        return readJson(Mansion.class, "JsonObjects.json");
    }
}
