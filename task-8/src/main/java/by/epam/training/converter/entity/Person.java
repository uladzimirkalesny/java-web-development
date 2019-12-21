package by.epam.training.converter.entity;

import by.epam.training.converter.adapter.IntegerIdAdapter;
import by.epam.training.converter.enums.WorkType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person", propOrder = {"id", "surname", "name", "birthday", "birthplace", "work"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @XmlAttribute(name = "ID", required = true)
    @XmlID
    @XmlJavaTypeAdapter(type = Integer.class, value = IntegerIdAdapter.class)
    private Integer id;
    @XmlElement(required = true)
    private String surname;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private Birthday birthday;
    @XmlElement(required = true)
    private Birthplace birthplace = new Birthplace();
    @XmlElement(required = true)
    private WorkType work;
}
