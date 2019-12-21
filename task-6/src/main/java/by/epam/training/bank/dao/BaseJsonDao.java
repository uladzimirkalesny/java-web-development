package by.epam.training.bank.dao;

import by.epam.training.bank.checker.SettingsChecker;
import by.epam.training.bank.entity.Discount;
import by.epam.training.bank.entity.showForImpl.ShowFor;
import by.epam.training.bank.gson.DiscountAdapter;
import by.epam.training.bank.gson.LocalDateAdapter;
import by.epam.training.bank.gson.ShowForAdapter;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import static by.epam.training.bank.constant.AppConstants.DATA_DIR;
import static by.epam.training.bank.constant.AppConstants.PATH_SEPARATOR;
import static by.epam.training.bank.constant.AppConstants.SETTINGS_PATTERN;

public abstract class BaseJsonDao<E> {

    static {
        GSON = new GsonBuilder().setPrettyPrinting()
                                .serializeNulls()
                                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                                .registerTypeAdapter(ShowFor.class, new ShowForAdapter())
                                .registerTypeAdapter(Discount.class, new DiscountAdapter())
                                .create();
    }

    private final static Gson GSON;
    private final SettingsChecker settingsChecker = new SettingsChecker();

    protected abstract Class<E> getRootObjectClass();

    protected abstract String getFileName();

    protected E getRootObject() {
        E object;

        JsonParser parser = new JsonParser();

        try (JsonReader reader = new JsonReader(new InputStreamReader(
                new FileInputStream(new File(DATA_DIR) + PATH_SEPARATOR + getFileName())))) {

            JsonElement dbRootElement = parser.parse(reader);
            JsonObject dbRootObject = dbRootElement.getAsJsonObject();

            checkFieldsIfSettingsJson(dbRootObject);

            object = deserialize(dbRootObject, getRootObjectClass());

        } catch (IOException e) {
            throw new NoSuchElementException("File not found " + DATA_DIR + PATH_SEPARATOR + getFileName());
        }
        return object;
    }

    private void checkFieldsIfSettingsJson(JsonObject dbRootObject) {
        if (getFileName().equals(SETTINGS_PATTERN)) {
            settingsChecker.isFullFieldsContains(dbRootObject.getAsJsonObject("settings"));
        }
    }

    private E deserialize(JsonObject object, Class<E> clazz) {
        return GSON.fromJson(object, clazz);
    }


    protected void rewriteJsonFile(E obj) {
        try {
            String toJson = GSON.toJson(obj);
            Files.write(Paths.get(new File(DATA_DIR) + PATH_SEPARATOR + getFileName()), toJson.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error during operation rewriting json file");
        }
    }
}
