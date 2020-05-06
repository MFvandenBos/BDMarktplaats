package domain;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author micha
 */
@XmlRootElement(name = "Receipient")
@XmlType(propOrder = {
        "street",
        "houseNumber",
        "postalCode",
        "city",
        "country"
})
@Embeddable
public class Address
{
    @XmlElement(name = "street")
    private String street;

    @XmlElement(name = "houseNumber")
    private int houseNumber;

    @XmlElement(name = "postalCode")
    private String postalCode;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "country")
    private String country;


    public Address(){}

    public Address(String Street, int HouseNumber, String PostalCode, String City){
        this.street = Street;
        this.houseNumber = HouseNumber;
        this.postalCode = PostalCode;
        this.city = City;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String outputAdress()
    {
        StringBuilder output = new StringBuilder("");
        output.append(street).append(String.valueOf(houseNumber)).append("\n");
        output.append(postalCode).append("\n");
        output.append(city).append("\n");
        output.append(country);
        return output.toString();
    }


}