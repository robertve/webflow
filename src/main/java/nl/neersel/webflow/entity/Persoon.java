package nl.neersel.webflow.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by robert on 7-5-14.
 */
@Entity
public class Persoon implements Serializable {
    private final Set<Adres> addessen = new HashSet<>();
    private Integer id;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;

    /**
     * Gets id (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    /**
     * Sets id (primary key).
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * Gets list of <code>Adres</code>sen.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSOON_ID", nullable = false)
    public Set<Adres> getAdressen() {
        return addessen;
    }
}
