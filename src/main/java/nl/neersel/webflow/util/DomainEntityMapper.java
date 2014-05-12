package nl.neersel.webflow.util;

import nl.neersel.webflow.domain.Address;
import nl.neersel.webflow.domain.Person;
import nl.neersel.webflow.entity.Adres;
import nl.neersel.webflow.entity.Persoon;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by robert on 9-5-14.
 */
@Component
public class DomainEntityMapper {

    public Adres map(Address address) {
        Adres adres = new Adres();

        adres.setStraat(address.getStreet());
        adres.setHuisnummer(address.getHouseNumber());
        adres.setPostcode(address.getPostalCode());
        adres.setWoonplaats(address.getCity());
        adres.setIngangsdatum(toDate(address.getMoveInDate()));
        adres.setEinddatum(toDate(address.getMoveOutDate()));
        return adres;
    }

    private Date toDate(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return new Date(calendar.getTime().getTime());
    }

    public Address map(Adres adres) {
        Address address = new Address();

        address.setStreet(adres.getStraat());
        address.setHouseNumber(adres.getHuisnummer());
        address.setPostalCode(adres.getPostcode());
        address.setCity(adres.getWoonplaats());
        address.setMoveInDate(toCalendar(adres.getIngangsdatum()));
        Date einddatum = adres.getEinddatum();
        if (einddatum != null) {
            address.setMoveOutDate(toCalendar(einddatum));
        }
        return address;
    }

    private GregorianCalendar toCalendar(Date date) {
        GregorianCalendar moveOutDate = new GregorianCalendar();
        moveOutDate.setTime(date);
        return moveOutDate;
    }

    public Persoon map(Person person) {
        Persoon persoon = new Persoon();
        persoon.setAchternaam(person.getLastName());
        persoon.setId(person.getId());
        persoon.setTussenvoegsel(person.getPrefix());
        persoon.setVoornaam(person.getFirstName());

        Map<Calendar, Address> addressMap = person.getAddressHistoryMap();
        for (Calendar calendar : addressMap.keySet()) {
            persoon.getAdressen().add(map(addressMap.get(calendar)));
        }
        return persoon;
    }

    public Person map(Persoon persoon) {
        Person person = new Person();
        person.setLastName(persoon.getAchternaam());
        person.setId(persoon.getId());
        person.setPrefix(persoon.getTussenvoegsel());
        person.setFirstName(persoon.getVoornaam());

        for (Adres adres : persoon.getAdressen()) {
            person.addAddressHistory(map(adres));
        }
        return person;
    }
}
