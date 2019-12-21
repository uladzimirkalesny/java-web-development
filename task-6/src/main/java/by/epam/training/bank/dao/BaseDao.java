package by.epam.training.bank.dao;

import by.epam.training.bank.entity.BaseEntity;
import by.epam.training.bank.entity.DepartmentBankData;
import by.epam.training.bank.entity.Discount;
import by.epam.training.bank.entity.HeadBankData;
import by.epam.training.bank.entity.showForImpl.ShowFor;
import by.epam.training.bank.gson.DiscountAdapter;
import by.epam.training.bank.gson.LocalDateAdapter;
import by.epam.training.bank.gson.ShowForAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static by.epam.training.bank.constant.AppConstants.DATABASE_PATTERN;
import static by.epam.training.bank.constant.AppConstants.DATA_DIR;
import static by.epam.training.bank.constant.AppConstants.PATH_SEPARATOR;

@lombok.Data
public abstract class BaseDao<T extends BaseEntity> implements CrudDao<T> {

    private Gson gson;

    {
        gson = new GsonBuilder().setPrettyPrinting()
                                .serializeNulls()
                                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                                .registerTypeAdapter(ShowFor.class, new ShowForAdapter())
                                .registerTypeAdapter(Discount.class, new DiscountAdapter())
                                .create();
    }

    public abstract List<T> updateDbData();

    protected abstract List<T> getEntityFromData();

    @Override
    public T findById(int id) {
        return getEntityFromData().stream()
                                  .filter(entity -> entity.getId() == id)
                                  .findFirst()
                                  .orElseThrow(() -> new RuntimeException("Entity with id " + id + " not found"));
    }

    public void create(T model) {
        List<T> entityFromData = getEntityFromData();
        entityFromData.add(model);
    }

    @Override
    public void update(T model) {
        //NOP
    }

    @Override
    public void delete(int id) {
        findAll().removeIf(entity -> entity.getId() == id);


    }

    @Override
    public List<T> findAll() {
        return getEntityFromData();
    }

    private <E> List<E> deserialize(JsonArray array, Class<E> clazz) {
        return StreamSupport.stream(array.spliterator(), false)
                            .map(element -> gson.fromJson(element, clazz))
                            .collect(Collectors.toList());
    }

    private <E> E deserialize(JsonObject object, Class<E> clazz) {
        return gson.fromJson(object, clazz);
    }

    protected HeadBankData getBankData() {
        HeadBankData bankData;
        JsonParser parser = new JsonParser();

        try (JsonReader reader = new JsonReader(new InputStreamReader(
                new FileInputStream(new File(DATA_DIR) + PATH_SEPARATOR + DATABASE_PATTERN)))) {

            JsonElement dbRootElement = parser.parse(reader);
            JsonObject dbRootObject = dbRootElement.getAsJsonObject();

            bankData = deserialize(dbRootObject, HeadBankData.class);

        } catch (IOException e) {
            throw new NoSuchElementException("File not found " + DATA_DIR + PATH_SEPARATOR + DATABASE_PATTERN);
        }
        return bankData;
    }

    protected DepartmentBankData getBankData(String department) {
        DepartmentBankData bankData;
        JsonParser parser = new JsonParser();

        try (JsonReader reader = new JsonReader(new InputStreamReader(
                new FileInputStream(new File(DATA_DIR) + PATH_SEPARATOR + department)))) {

            JsonElement rootElement = parser.parse(reader);
            JsonObject rootObject = rootElement.getAsJsonObject();

            bankData = deserialize(rootObject, DepartmentBankData.class);

        } catch (IOException e) {
            throw new NoSuchElementException("File not found " + DATA_DIR + PATH_SEPARATOR + department);
        }

        return bankData;
    }

    protected static void rewriteJsonFile(String toJson, String filePath) {
        try {
            Files.write(Paths.get(filePath), toJson.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error during operation rewriting json file");
        }
    }
}
