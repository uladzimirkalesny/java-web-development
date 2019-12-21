package by.epam.training.converter.entity;

import by.epam.training.converter.adapter.DayAdapter;
import by.epam.training.converter.adapter.MonthAdapter;
import by.epam.training.converter.adapter.YearAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "birthday", propOrder = {"day", "month", "year"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Birthday {
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(value = DayAdapter.class)
    private Integer day;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(value = MonthAdapter.class)
    private Integer month;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(value = YearAdapter.class)
    private Integer year;
}