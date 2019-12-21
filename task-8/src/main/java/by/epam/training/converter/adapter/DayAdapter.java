package by.epam.training.converter.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DayAdapter extends XmlAdapter<String, Integer> {
    @Override
    public Integer unmarshal(String v) {
        return Integer.parseInt(v);
    }

    @Override
    public String marshal(Integer v) {
        return String.valueOf(v);
    }
}
