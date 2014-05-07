package nl.neersel.webflow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by robert on 7-5-14.
 */
@Entity
public class Adres implements Serializable {
    private Integer id;
    private String straat;
    private String huisnummer;
    private String postcode;
    private String woonplaats;
    private Date ingangsdatum;
    private Date einddatum;
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

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public Date getIngangsdatum() {
        return ingangsdatum;
    }

    public void setIngangsdatum(Date ingangsdatum) {
        this.ingangsdatum = ingangsdatum;
    }

    public Date getEinddatum() {
        return einddatum;
    }

    public void setEinddatum(Date einddatum) {
        this.einddatum = einddatum;
    }
}
