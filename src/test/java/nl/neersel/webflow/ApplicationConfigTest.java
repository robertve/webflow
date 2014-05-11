package nl.neersel.webflow;

import nl.neersel.webflow.entity.Persoon;
import nl.neersel.webflow.repository.PersoonRepository;
import nl.neersel.webflow.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ApplicationConfigTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private UrlBasedViewResolver viewResolver;
    @Autowired
    private PersoonRepository persoonRepository;
    @Autowired
    private PersonService personService;

    @Test
    public void dataSource() throws Exception {
        assertNotNull(dataSource);
    }

    @Test
    public void entityManagerFactory() throws Exception {
        assertNotNull(entityManagerFactory);
    }

    @Test
    public void transactionManager() throws Exception {
        assertNotNull(transactionManager);
    }

    @Test
    public void setupViewResolver() throws Exception {
        assertNotNull(viewResolver);
    }

    @Test
    public void repositoryGetAllPersonen() throws Exception {
        List<Persoon> personen = persoonRepository.findAll();
        assertNotNull(personen.get(0));
    }

    @Test
    public void autowirePersonService() throws Exception {
        assertNotNull(personService);
    }

}