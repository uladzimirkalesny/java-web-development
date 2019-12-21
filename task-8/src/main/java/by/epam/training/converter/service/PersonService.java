package by.epam.training.converter.service;

import by.epam.training.converter.entity.People;
import by.epam.training.converter.entity.Person;
import lombok.Data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Data
public class PersonService {
    private JAXBContext context;
    private final Class clazz = People.class;

    public PersonService() {
        try {
            this.context = JAXBContext.newInstance(clazz);
        } catch (JAXBException e) {
            throw new RuntimeException("an error was encountered while creating the JAXBContext" + e.getMessage());
        }
    }

    public List<Person> getPersonsFromXml(File xmlFile) {
        List<Person> persons;
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fileReader = new FileReader(xmlFile);
            People peopleList = (People) unmarshaller.unmarshal(fileReader);

            persons = peopleList.getPeople();
        } catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException("Error with instantiated unmarshaller and his actions " + e.getMessage());
        }
        return persons;
    }

    public void addPersonsToXmlWithPrintActionToConsole(List<Person> people, File xmlFile) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            People peopleList = new People();
            peopleList.setPeople(people);

            marshaller.marshal(peopleList, xmlFile);
            marshaller.marshal(peopleList, System.out);
        } catch (JAXBException e) {
            throw new RuntimeException("Error with instantiated marshaller and his actions " + e.getMessage());
        }
    }
}
