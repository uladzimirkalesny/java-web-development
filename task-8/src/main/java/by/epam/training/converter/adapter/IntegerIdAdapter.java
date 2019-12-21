package by.epam.training.converter.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class IntegerIdAdapter extends XmlAdapter<String, Integer> {
    @Override
    public String marshal(Integer id) {
        return id.toString();
    }

    @Override
    public Integer unmarshal(String id) {
        return Integer.parseInt(id);
    }
}