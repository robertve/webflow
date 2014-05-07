package nl.neersel.webflow.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PersonTest {

    private int personId = 0;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void addAddressHistoryEmptyAddress() throws Exception {
        Person person = createPerson();
        exception.expect(NullPointerException.class);
        person.addAddressHistory(createAddress());
    }

    @Test
    public void addAddressHistoryInvalidAddress() throws Exception {
        Person person = createPerson();
        Address address0 = createAddress();
        GregorianCalendar moveInDay0 = new GregorianCalendar();
        address0.setMoveInDate(moveInDay0);
        GregorianCalendar moveOutDay0 = new GregorianCalendar();
        moveOutDay0.add(Calendar.MONTH, -1);
        address0.setMoveOutDate(moveOutDay0);

        exception.expect(IllegalStateException.class);
        person.addAddressHistory(address0);
    }

    @Test
    public void addAddressHistoryFirstAddress() throws Exception {
        Person person = createPerson();
        Address address = createAddress();
        address.setMoveInDate(new GregorianCalendar());
        person.addAddressHistory(address);
        TreeMap<Calendar, Address> expected = new TreeMap<Calendar, Address>() {{
            put(address.getMoveInDate(), address);
        }};
        assertEquals(expected, person.getAddressHistoryMap());
    }

    @Test
    public void addAddressHistorySecondAddressLaterNoEndDate() throws Exception {
        Person person = createPerson();
        Address address0 = createAddress();
        GregorianCalendar day0 = new GregorianCalendar();
        day0.add(Calendar.MONTH, -10);
        address0.setMoveInDate(day0);
        person.addAddressHistory(address0);

        Address address1 = createAddress();
        GregorianCalendar day1 = new GregorianCalendar();
        address1.setMoveInDate(day1);
        person.addAddressHistory(address1);

        // Apply the expected change to the first address.
        Address expectedAddress0 = createAddress();
        expectedAddress0.setMoveInDate(day0);
        expectedAddress0.setMoveOutDate(day1);

        TreeMap<Calendar, Address> expected = new TreeMap<Calendar, Address>() {{
            put(expectedAddress0.getMoveInDate(), expectedAddress0);
            put(address1.getMoveInDate(), address1);
        }};
        assertEquals(expected, person.getAddressHistoryMap());
    }

    @Test
    public void addAddressHistorySecondAddressLaterWithEndDate() throws Exception {
        Person person = createPerson();
        Address address0 = createAddress();
        GregorianCalendar moveInDay0 = new GregorianCalendar();
        moveInDay0.add(Calendar.MONTH, -10);
        address0.setMoveInDate(moveInDay0);
        GregorianCalendar moveOutDay0 = new GregorianCalendar();
        moveOutDay0.add(Calendar.MONTH, -1);
        address0.setMoveOutDate(moveOutDay0);
        person.addAddressHistory(address0);

        Address address1 = createAddress();
        address1.setMoveInDate((Calendar) moveOutDay0.clone());
        person.addAddressHistory(address1);

        TreeMap<Calendar, Address> expected = new TreeMap<Calendar, Address>() {{
            put(address0.getMoveInDate(), address0);
            put(address1.getMoveInDate(), address1);
        }};
        assertEquals(expected, person.getAddressHistoryMap());
    }

    @Test
    public void addAddressHistorySecondAddressSoonerNoEndDate() throws Exception {
        Person person = createPerson();
        Address address0 = createAddress();
        GregorianCalendar day0 = new GregorianCalendar();
        day0.add(Calendar.MONTH, 10);
        address0.setMoveInDate(day0);
        person.addAddressHistory(address0);

        Address address1 = createAddress();
        GregorianCalendar day1 = new GregorianCalendar();
        address1.setMoveInDate(day1);
        exception.expect(IllegalStateException.class);
        person.addAddressHistory(address1);
    }

    @Test
    public void addAddressHistorySecondAddressSoonerWithEndDate() throws Exception {
        Person person = createPerson();
        Address address0 = createAddress();
        GregorianCalendar moveInDay0 = new GregorianCalendar();
        moveInDay0.add(Calendar.MONTH, 10);
        address0.setMoveInDate(moveInDay0);
        person.addAddressHistory(address0);

        Address address1 = createAddress();
        GregorianCalendar moveInDay1 = new GregorianCalendar();
        moveInDay1.add(Calendar.MONTH, -10);
        GregorianCalendar moveOutDay1 = new GregorianCalendar();
        address1.setMoveInDate(moveInDay1);
        address1.setMoveOutDate(moveOutDay1);
        person.addAddressHistory(address1);

        TreeMap<Calendar, Address> expected = new TreeMap<Calendar, Address>() {{
            put(address0.getMoveInDate(), address0);
            put(address1.getMoveInDate(), address1);
        }};
        assertEquals(expected, person.getAddressHistoryMap());
    }

    @Test
    public void addAddressHistorySecondAddressSoonerOverlap() throws Exception {
        Person person = createPerson();
        Address address0 = createAddress();
        GregorianCalendar moveInDay0 = new GregorianCalendar();
        address0.setMoveInDate(moveInDay0);
        person.addAddressHistory(address0);

        Address address1 = createAddress();
        GregorianCalendar moveInDay1 = new GregorianCalendar();
        moveInDay1.add(Calendar.MONTH, -10);
        address1.setMoveInDate(moveInDay1);
        GregorianCalendar moveOutDay1 = new GregorianCalendar();
        moveOutDay1.add(Calendar.MONTH, 1);
        address1.setMoveOutDate(moveOutDay1);

        exception.expect(IllegalStateException.class);
        person.addAddressHistory(address1);
    }

    @Test
    public void addAddressHistorySecondAddressLaterOverlap() throws Exception {
        Person person = createPerson();
        Address address0 = createAddress();
        GregorianCalendar moveInDay0 = new GregorianCalendar();
        address0.setMoveInDate(moveInDay0);
        GregorianCalendar moveOutDay0 = new GregorianCalendar();
        moveOutDay0.add(Calendar.MONTH, 10);
        address0.setMoveOutDate(moveOutDay0);
        person.addAddressHistory(address0);

        Address address1 = createAddress();
        GregorianCalendar moveInDay1 = new GregorianCalendar();
        moveInDay1.add(Calendar.MONTH, 1);
        address1.setMoveInDate(moveInDay1);

        exception.expect(IllegalStateException.class);
        person.addAddressHistory(address1);
    }

    private Address createAddress() {
        Address address = new Address();
        address.setStreet("Test");
        address.setHouseNumber("123a");
        address.setPostalCode("3068AX");
        address.setCity("Test City");
        return address;
    }

    private Person createPerson() {
        Person person = new Person();
        person.setId(++personId);
        person.setFirstName("Test");
        person.setPrefix("van");
        person.setLastName("Last");
        return person;
    }
}