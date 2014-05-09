package nl.neersel.webflow.service;

import nl.neersel.webflow.domain.Person;
import nl.neersel.webflow.exception.PersonNotFoundException;
import nl.neersel.webflow.repository.PersoonRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by robert on 9-5-14.
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    private PersoonRepository persoonRepository;

    @Override
    @Transactional
    public Person create(Person person) {
        return null;
    }

    @Override
    @Transactional
    public Person delete(int id) throws PersonNotFoundException {
        return null;
    }

    @Override
    @Transactional
    public List<Person> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Person update(Person person) throws PersonNotFoundException {
        return null;
    }

    @Override
    @Transactional
    public Person findById(int id) {
        return null;
    }
}
