package by.epam.training.converter.dom;

import by.epam.training.converter.entity.Person;
import by.epam.training.converter.enums.WorkType;
import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Objects;
import java.util.Random;

@Data
public class PeopleDomBuilder {
    private File file;
    private DocumentBuilder builder;
    private Document document;

    public PeopleDomBuilder(File file) {
        this.file = file;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            this.builder = factory.newDocumentBuilder();
            this.document = builder.parse(file);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("The parser configuration is missing " + e.getMessage());
        } catch (SAXException | IOException e) {
            throw new RuntimeException("Processing file failure " + e.getMessage());
        }
    }

    public void insertPerson(Person person) {

        Document newDocument = builder.newDocument();
        Element newPeopleRootElement = newDocument.createElement("people");

        Element peopleRootElement = document.getDocumentElement();

        NodeList persons = peopleRootElement.getElementsByTagName("person");

        for (int i = 0; i < persons.getLength(); i++) {
            Element personElement = (Element) persons.item(i);
            Integer personId = Integer.valueOf(personElement.getAttribute("ID"));

            if (Objects.equals(personId, person.getId())) {
                newPeopleRootElement.appendChild(newDocument.importNode(createPersonNode(person), true));
            }

            if (personId >= person.getId()) {
                personElement.setAttribute("ID", String.valueOf(++personId));
            }

            newPeopleRootElement.appendChild(newDocument.importNode(personElement, true));
        }

        newDocument.appendChild(newPeopleRootElement);

        document = newDocument;
        document.getDocumentElement().normalize();
    }

    private Element createPersonNode(Person person) {

        Element personElement = document.createElement("person");
        personElement.setAttribute("ID", String.valueOf(person.getId()));

        Element surname = document.createElement("surname");
        surname.appendChild(document.createTextNode(person.getSurname()));

        Element name = document.createElement("name");
        name.appendChild(document.createTextNode(person.getName()));

        Element birthday = document.createElement("birthday");

        Element day = document.createElement("day");
        day.appendChild(document.createTextNode(String.format("%02d", person.getBirthday().getDay())));

        Element month = document.createElement("month");
        month.appendChild(document.createTextNode(String.format("%02d", person.getBirthday().getMonth())));

        Element year = document.createElement("year");
        year.appendChild(document.createTextNode(String.format("%04d", person.getBirthday().getYear())));

        birthday.appendChild(day);
        birthday.appendChild(month);
        birthday.appendChild(year);

        Element birthplace = document.createElement("birthplace");
        birthplace.setAttribute("city", person.getBirthplace().getCity());

        Element work = document.createElement("work");
        work.appendChild(document.createTextNode(person.getWork().value()));

        personElement.appendChild(surname);
        personElement.appendChild(name);
        personElement.appendChild(birthday);
        personElement.appendChild(birthplace);
        personElement.appendChild(work);

        return personElement;
    }

    public void changeRandomWorkPersonWithNameContainsILetter() {

        NodeList persons = document.getElementsByTagName("person");

        for (int i = 0; i < persons.getLength(); i++) {
            Element person = (Element) persons.item(i);

            String name = person.getElementsByTagName("name").item(0).getTextContent();

            if (name.startsWith("I")) {
                WorkType randomWork = WorkType.values()[new Random().nextInt(WorkType.values().length)];
                person.getElementsByTagName("work").item(0).setTextContent(randomWork.value());
            }
        }
    }

    public void prettyPrint() {
        StringWriter strWriter;

        try {
            Transformer transformer = getTransformer();

            Source source = new DOMSource(document);

            strWriter = new StringWriter();

            Result result = new StreamResult(strWriter);

            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException("Unrecoverable error occurs during the course of the transformation " + e.getMessage());
        }

        System.out.println(strWriter.getBuffer());
    }


    public void writeToXml(File xmlFile) {
        try {
            Transformer transformer = getTransformer();

            Source src = new DOMSource(document);

            Result result = new StreamResult(new FileOutputStream(xmlFile));

            transformer.transform(src, result);
        } catch (FileNotFoundException | TransformerException e) {
            throw new RuntimeException("Error processing and transforming xml file " + e.getMessage());
        }
    }

    private Transformer getTransformer() {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "4");
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException("Error to getting new instance transformer " + e.getMessage());
        }

        return transformer;
    }
}
