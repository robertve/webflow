package nl.neersel.webflow.domain;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by robert on 7-5-14.
 */
public class Address implements Cloneable, Serializable {
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private Calendar moveInDate;
    private Calendar moveOutDate;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Calendar getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(Calendar moveInDate) {
        this.moveInDate = moveInDate;
    }

    public Calendar getMoveOutDate() {
        return moveOutDate;
    }

    public void setMoveOutDate(Calendar moveOutDate) {
        this.moveOutDate = moveOutDate;
    }

    public Address clone() {
        Address clone = new Address();
        clone.setMoveInDate((Calendar) getMoveInDate().clone());
        if (getMoveOutDate() != null) {
            clone.setMoveOutDate((Calendar) getMoveOutDate().clone());
        }
        clone.setStreet(getStreet());
        clone.setHouseNumber(getHouseNumber());
        clone.setPostalCode(getPostalCode());
        clone.setCity(getCity());
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (!city.equals(address.city)) return false;
        if (!moveInDate.equals(address.moveInDate)) return false;
        if (moveOutDate != null ? !moveOutDate.equals(address.moveOutDate) : address.moveOutDate != null) return false;
        if (!houseNumber.equals(address.houseNumber)) return false;
        if (!postalCode.equals(address.postalCode)) return false;
        if (!street.equals(address.street)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + houseNumber.hashCode();
        result = 31 * result + postalCode.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + moveInDate.hashCode();
        result = 31 * result + (moveOutDate != null ? moveOutDate.hashCode() : 0);
        return result;
    }
}
