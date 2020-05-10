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
    private Integer houseNumber;

    @XmlElement(name = "postalCode")
    private String postalCode;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "country")
    private String country;

    private boolean onbekend;

    public Address(){
        onbekend = true;
    }

    public Address(String Street, int HouseNumber, String PostalCode, String City){
        onbekend = false;
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
        onbekend = false;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
        onbekend = false;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        onbekend = false;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        onbekend = false;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        onbekend = false;
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

    @Override
    public String toString() {
        if(onbekend){return "onbekend";}
        else{
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
        }
    }

    public boolean isOnbekend() {
        return onbekend;
    }
}