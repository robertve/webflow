package nl.neersel.webflow.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import nl.neersel.webflow.ApplicationConfig;
import nl.neersel.webflow.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class PersonServiceImplTest {

    @Autowired
    private PersonService personService;

    @Test
    public void create() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    @DatabaseSetup("personen.xml")
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
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void moveIn() throws Exception {

    }

    @Test
    public void findById() throws Exception {

    }
}