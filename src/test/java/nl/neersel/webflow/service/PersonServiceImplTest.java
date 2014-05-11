package nl.neersel.webflow.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import nl.neersel.webflow.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceApplicationConfig.class)
public class PersonServiceImplTest {

    @Autowired
    private PersonServiceImpl personService;

    @Test
    public void create() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    @DatabaseSetup("persoon.xml")
    public void findAll() throws Exception {
        List<Person> allPersons = personService.findAll();
        assertEquals(1, allPersons.size());
        final Person person0 = allPersons.get(0);
        assertEquals("Robert", person0.getFirstName());
        assertEquals("van", person0.getPrefix());
        assertEquals("Eersel", person0.getLastName());
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