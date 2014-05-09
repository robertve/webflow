package nl.neersel.webflow.repository;

import nl.neersel.webflow.entity.Persoon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by robert on 9-5-14.
 */
public interface PersoonRepository extends JpaRepository<Persoon, Integer> {
}
