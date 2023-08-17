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

//    public static <T> T readJson(Class<T> clazz, File file) throws IOException {
//        return readJson(clazz, new FileInputStream(file));
////        return readJson(clazz, JsonReader.class.getClassLoader().getResourceAsStream(file.getPath()));
//    }

    public static String readFileAsString(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    public static <T> T readJson(Class<T> clazz, File file) throws IOException {
        String jsonString = readFileAsString(file);
        return new Gson().fromJson(jsonString, clazz);
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
        // create a new file object
        File file = new File(filename);
        // check if the file exists
//        return file.exists() ? readJson(Mansion.class, file) : readJson(Mansion.class, "JsonObjects.json");
        if(file.exists()){
            return readJson(Mansion.class, file);
        } else {
            return readJson(Mansion.class, "JsonObjects.json");
        }
    }
}
