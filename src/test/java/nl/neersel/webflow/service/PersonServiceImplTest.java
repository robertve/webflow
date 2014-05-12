package nl.neersel.webflow.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import nl.neersel.webflow.ApplicationConfig;
import nl.neersel.webflow.domain.Address;
import nl.neersel.webflow.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class PersonServiceImplTest {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    private PersonService personService;

    @Test
    public void create() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    @DatabaseSetup("/personen.xml")
    public void findAll() throws Exception {
        List<Person> allPersons = personService.findAll();
        assertEquals(2, allPersons.size());
        final Person person0 = new Person();
        person0.setId(1);
        person0.setFirstName("Robert");
        person0.setPrefix("van");
        person0.setLastName("Eersel");
        final Person person1 = new Person();
        person1.setId(2);
        person1.setFirstName("Herman");
        person1.setPrefix("van");
        person1.setLastName("Eersel");
        assertEquals(Arrays.asList(person0, person1), allPersons);
        final Map<Calendar, Address> addressHistoryMap0 = allPersons.get(0).getAddressHistoryMap();
        assertEquals(2, addressHistoryMap0.size());
    }

    @Test
    @DatabaseSetup("/personen.xml")
    @ExpectedDatabase(value = "/personen_na_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void update() throws Exception {
        List<Person> allPersons = personService.findAll();
        Person person0 = allPersons.get(0);

        // Change person data.
        person0.setFirstName("Robert Alexander");

        // Add an address to the history.
        final Address address0 = new Address();
        address0.setStreet("Westnieuwland");
        address0.setHouseNumber("6");
        address0.setPostalCode("3131 VX");
        address0.setCity("Vlaardingen");
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(DATE_FORMAT.parse("01-02-2013"));
        address0.setMoveInDate(calendar);
        person0.addAddressHistory(address0);

        personService.update(person0);
    }

    @Test
    public void moveIn() throws Exception {

    }

    @Test
    public void findById() throws Exception {

    }
}