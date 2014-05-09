package nl.neersel.webflow.service;

import nl.neersel.webflow.domain.Address;
import nl.neersel.webflow.domain.Person;
import nl.neersel.webflow.entity.Adres;
import nl.neersel.webflow.entity.Persoon;
import nl.neersel.webflow.exception.InvalidMoveOperationException;
import nl.neersel.webflow.exception.PersonNotFoundException;
import nl.neersel.webflow.repository.PersoonRepository;
import nl.neersel.webflow.util.DomainEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 9-5-14.
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    private PersoonRepository persoonRepository;

    @Autowired
    private DomainEntityMapper domainEntityMapper;

    @Override
    @Transactional
    public Person create(Person person) {
        Persoon persoon = domainEntityMapper.map(person);
        persoonRepository.save(persoon);
        return domainEntityMapper.map(persoon);
    }

    @Override
    @Transactional
    public Person delete(int id) throws PersonNotFoundException {
        Persoon persoon = persoonRepository.findOne(id);
        if (persoon == null) {
            throw new PersonNotFoundException("Person with id " + id + " could not be found.");
        }
        persoonRepository.delete(persoon);
        persoon.setId(null);
        return domainEntityMapper.map(persoon);
    }

    @Override
    @Transactional
    public List<Person> findAll() {
        List<Person> result = new ArrayList<>();
        for (Persoon persoon : persoonRepository.findAll()) {
            result.add(domainEntityMapper.map(persoon));
        }
        return result;
    }

    @Override
    @Transactional
    public Person update(Person person) throws PersonNotFoundException {
        Persoon persistedPersoon = getPersistedPersoon(person);

        // Find existing addresses based on move in date.
        Persoon transientPersoon = domainEntityMapper.map(person);
        for (Adres adres : transientPersoon.getAdressen()) {
            // Find the existing personId (if present).
            Adres persistedAdres = persistedPersoon.findAdres(adres.getIngangsdatum());
            if (persistedAdres != null) {
                adres.setId(persistedAdres.getId());
            }
        }
        persistedPersoon = null;
        persoonRepository.save(transientPersoon);

        return domainEntityMapper.map(transientPersoon);
    }

    private Persoon getPersistedPersoon(Person person) throws PersonNotFoundException {
        int personId = person.getId();
        Persoon persistedPersoon = persoonRepository.findOne(personId);
        if (persistedPersoon == null) {
            throw new PersonNotFoundException("Person with id " + personId + " could not be found.");
        }
        return persistedPersoon;
    }

    @Override
    @Transactional
    public Person moveIn(Person person, Address address) throws PersonNotFoundException, InvalidMoveOperationException {
        // Get a version of the existing person.
        person = domainEntityMapper.map(getPersistedPersoon(person));
        try {
            person.addAddressHistory(address);
        } catch (Exception e) {
            throw new InvalidMoveOperationException("Unable to process move in request at address " +
                    address.getPostalCode() + " - " + address.getHouseNumber(), e);
        }
        return update(person);
    }

    @Override
    @Transactional
    public Person findById(int id) {
        Persoon persoon = persoonRepository.findOne(id);
        if (persoon == null) {
            return null;
        }
        return domainEntityMapper.map(persoon);
    }
}
