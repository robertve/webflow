package nl.neersel.webflow.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by robert on 7-5-14.
 */
public class Person implements Cloneable, Serializable {
    private Integer id;
    private String firstName;
    private String prefix;
    private String lastName;
    private final Map<Calendar, Address> addressHistoryMap = new TreeMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addAddressHistory(Address newAddress) {
        // Look for invalid data.
        Calendar newMoveInDate = newAddress.getMoveInDate();
        if (newMoveInDate == null) {
            throw new NullPointerException("Null values are not allowed for move in dates.");
        }
        Calendar newMoveOutDate = newAddress.getMoveOutDate();
        if (newMoveOutDate != null && ! newMoveOutDate.after(newMoveInDate)) {
            throw new IllegalStateException("Move in date must be before the move out date.");
        }

        // Look for conflicts with existing addresses.
        Address latestAddress = null;
        for (Address address : addressHistoryMap.values()) {
            latestAddress = address;
            // If an existing move out date is empty then the new period must not overlap.
            if (address.getMoveOutDate() == null) {
                if (newMoveInDate.before(address.getMoveInDate()) &&
                        (newMoveOutDate == null || newMoveOutDate.after(address.getMoveInDate()))) {
                    throw new IllegalStateException("Addresses cannot overlap in time.");
                }
            } else {
                if (newMoveInDate.before(address.getMoveOutDate()) &&
                        (newMoveOutDate == null || newMoveOutDate.after(address.getMoveInDate()))) {
                    throw new IllegalStateException("Addresses cannot overlap in time.");
                }
            }
        }

        // Change the end date of the latest previous address if it is empty and the new address is later.
        if (latestAddress != null && latestAddress.getMoveOutDate() == null &&
                latestAddress.getMoveInDate().before(newMoveInDate)) {
            latestAddress.setMoveOutDate(newMoveInDate);
        }
        addressHistoryMap.put(newMoveInDate, newAddress.clone());
    }

    public Map<Calendar, Address> getAddressHistoryMap() {
        TreeMap<Calendar, Address> treeMap = new TreeMap<>();
        // Perform a deep copy.
        for (Address address : addressHistoryMap.values()) {
            treeMap.put(address.getMoveInDate(), address.clone());
        }
        return treeMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!id.equals(person.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
