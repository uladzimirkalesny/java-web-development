package by.epam.training.converter;

import by.epam.training.converter.comparator.PersonAgeComparator;
import by.epam.training.converter.dom.PeopleDomBuilder;
import by.epam.training.converter.entity.Birthday;
import by.epam.training.converter.entity.Birthplace;
import by.epam.training.converter.entity.Person;
import by.epam.training.converter.enums.WorkType;
import by.epam.training.converter.service.PersonService;
import by.epam.training.converter.utils.PeopleIndexRefresher;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {
    public static void main(String[] args) {
        String dir = new File(Runner.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
        File file = new File(dir + "/people.xml");

        PeopleDomBuilder builder = new PeopleDomBuilder(file);

        System.out.println("==========Current XML file before modifying===========");
        builder.prettyPrint();

        Person person = new Person(3,
                                   "Joshua",
                                   "Bloch",
                                   new Birthday(28, 8, 1961),
                                   new Birthplace("Suffolk County"),
                                   WorkType.SUN);

        builder.insertPerson(person);
        builder.writeToXml(file);

        System.out.println("==========After adding person to position 3 ===========");
        builder.prettyPrint();

        builder.changeRandomWorkPersonWithNameContainsILetter();
        builder.writeToXml(file);

        System.out.println("======After changing person with name contains I=======");
        builder.prettyPrint();

        PersonService personService = new PersonService();
        List<Person> people = personService.getPersonsFromXml(file);

        List<Person> sortedPeopleList = people.stream()
                                              .sorted(new PersonAgeComparator())
                                              .collect(Collectors.toList());

        System.out.println("=====================After sorting=====================");
        personService.addPersonsToXmlWithPrintActionToConsole(sortedPeopleList, file);

        System.out.println("===========Result XML file after refreshing============");
        PeopleIndexRefresher indexRefresher = new PeopleIndexRefresher();
        personService.addPersonsToXmlWithPrintActionToConsole(indexRefresher.refresh(sortedPeopleList), file);
    }
}
