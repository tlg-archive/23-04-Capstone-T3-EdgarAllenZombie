package eaz.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JsonReader {

    public static <T> T readJson(Class<T> clazz, String filename) throws IOException {
        return readJson(clazz, JsonReader.class.getClassLoader().getResourceAsStream(filename));
    }

    public static <T> T readJson(Class<T> clazz, InputStream stream) throws IOException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        try (Reader reader = new InputStreamReader(stream)) {
            return gson.fromJson(reader, clazz);
        }
    }

    public static <T> T readJson(Class<T> clazz, File file) throws IOException {
        return readJson(clazz, new FileInputStream(file));
    }

    public void writeJson(Object object, File file) throws IOException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(object, writer);
        }
    }

    public static Mansion readMansion(String filename) throws IOException {
        return readJson(Mansion.class, filename);
    }
}
