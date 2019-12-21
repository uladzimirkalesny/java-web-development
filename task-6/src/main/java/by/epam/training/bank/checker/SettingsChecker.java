package by.epam.training.bank.checker;

import by.epam.training.bank.exception.SettingsMissFieldException;
import com.google.gson.JsonObject;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class SettingsChecker {
    private Map<String, String> paramMap;

    {
        paramMap = new HashMap<>();

        paramMap.put("dateFrom", "Field dateFrom from settings.json is missing");
        paramMap.put("dateTo", "Field dateTo from settings.json is missing");
        paramMap.put("showFor", "Field showFor from settings.json is missing");
        paramMap.put("sortBy", "Field sortBy from settings.json is missing");
        paramMap.put("useDepartments", "Field useDepartments from settings.json is missing");
        paramMap.put("startCostEUR", "Field startCostEUR from settings.json is missing");
        paramMap.put("startCostUSD", "Field startCostUSD from settings.json is missing");
    }

    public boolean isFullFieldsContains(JsonObject settings) {
        boolean result = true;

        paramMap.keySet()
                .forEach(param -> {
                    if (settings.get(param) == null) {
                        throw new SettingsMissFieldException(paramMap.get(param));
                    }
                });

        return result;
    }
}
