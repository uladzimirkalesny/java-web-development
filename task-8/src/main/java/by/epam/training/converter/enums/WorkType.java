package by.epam.training.converter.enums;

import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "work")
@XmlEnum
@NoArgsConstructor
public enum WorkType {
    EPAM, IBA, SUN;

    public String value() {
        return name().toUpperCase();
    }

    public static WorkType fromValue(String v) {
        return valueOf(v);
    }
}
