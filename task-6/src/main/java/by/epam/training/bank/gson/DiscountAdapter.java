package by.epam.training.bank.gson;

import by.epam.training.bank.entity.Discount;
import by.epam.training.bank.entity.discountImpl.ManyDayDiscount;
import by.epam.training.bank.entity.discountImpl.OneDayDiscount;
import by.epam.training.bank.enums.DiscountType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;

public class DiscountAdapter implements JsonDeserializer<Discount>, JsonSerializer<Discount> {
    @Override
    public Discount deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement element = jsonObject.get("type");

        Discount discount = null;

        if (element.getAsString().equals("ONE")) {
            discount = context.deserialize(jsonObject, OneDayDiscount.class);
        } else if (element.getAsString().equals("MANY")) {
            discount = context.deserialize(jsonObject, ManyDayDiscount.class);
        }

        return discount;
    }

    @Override
    public JsonElement serialize(Discount src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        result.add("id", context.serialize(src.getId()));
        JsonElement type = context.serialize(src.getType());
        result.add("type", type);
        if (type.getAsString().equals(DiscountType.MANY.name())) {
            ManyDayDiscount manyDayDiscount = (ManyDayDiscount) src;

            result.add("dateFrom",
                       new JsonPrimitive(manyDayDiscount.getDateFrom()
                                                        .format(DateTimeFormatter.ISO_LOCAL_DATE)));
            result.add("dateTo", new JsonPrimitive(manyDayDiscount.getDateTo()
                                                                  .format(DateTimeFormatter.ISO_LOCAL_DATE)));
        } else {
            result.add("date",
                       new JsonPrimitive(((OneDayDiscount) src).getDate()
                                                               .format(DateTimeFormatter.ISO_LOCAL_DATE)));
        }
        result.add("discount", context.serialize(src.getDiscount()));

        return result;
    }
}
