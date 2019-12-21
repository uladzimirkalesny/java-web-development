package by.epam.training.bank.converter;

import by.epam.training.bank.enums.CurrencyType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
public class MoneyConverter {
    private Map<CurrencyType, BigDecimal> currencyBucket = new HashMap<>();
    private final Map<CurrencyType, BigDecimal> DEFAULT_CURRENCY_BUCKET;

    public MoneyConverter(BigDecimal costUSD, BigDecimal costEUR) {
        HashMap<CurrencyType, BigDecimal> map = new HashMap<>();

        map.put(CurrencyType.USD, costUSD);
        map.put(CurrencyType.EUR, costEUR);
        map.put(CurrencyType.BR, new BigDecimal(1));

        DEFAULT_CURRENCY_BUCKET = Collections.unmodifiableMap(map);

        currencyBucket.putAll(map);
    }

    public void updateCurrencyCost(CurrencyType type, BigDecimal value) {
        currencyBucket.put(type, value);
    }

    public BigDecimal convert(CurrencyType type, BigDecimal value) {
        return currencyBucket.get(type).multiply(value);
    }

    public void resetCurrencyBucket() {
        currencyBucket.clear();
        currencyBucket.putAll(DEFAULT_CURRENCY_BUCKET);
    }
}
