package nl.neersel.webflow.repository;

import nl.neersel.webflow.domain.Person;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by robert on 9-5-14.
 */
public interface PersoonRepository extends Repository<Person, Long> {
    List<Person> findByLastname(String lastname);
}
