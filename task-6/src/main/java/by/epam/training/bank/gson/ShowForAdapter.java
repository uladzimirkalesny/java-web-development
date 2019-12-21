package by.epam.training.bank.gson;

import by.epam.training.bank.entity.showForImpl.ShowFor;
import by.epam.training.bank.entity.showForImpl.ShowForId;
import by.epam.training.bank.entity.showForImpl.ShowForName;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ShowForAdapter implements JsonDeserializer<ShowFor> {

    @Override
    public ShowFor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement element = jsonObject.get("type");

        ShowFor showFor = null;

        if (element.getAsString().equals("ID")) {
            showFor = context.deserialize(jsonObject, ShowForId.class);
        } else if (element.getAsString().equals("NAME")) {
            showFor = context.deserialize(jsonObject, ShowForName.class);
        }

        return showFor;
    }
}
