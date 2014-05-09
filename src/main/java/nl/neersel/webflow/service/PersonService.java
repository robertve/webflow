package nl.neersel.webflow.service;

import nl.neersel.webflow.domain.Address;
import nl.neersel.webflow.domain.Person;
import nl.neersel.webflow.exception.InvalidMoveOperationException;
import nl.neersel.webflow.exception.PersonNotFoundException;

import java.util.List;

/**
 * Created by robert on 9-5-14.
 */
public interface PersonService {
    public Person create(Person person);

    public Person delete(int id) throws PersonNotFoundException;

    public List<Person> findAll();

    public Person update(Person person) throws PersonNotFoundException;

    public Person moveIn(Person person, Address address) throws PersonNotFoundException, InvalidMoveOperationException;

    public Person findById(int id);
}
