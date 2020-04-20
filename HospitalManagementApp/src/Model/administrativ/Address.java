package Model.administrativ;
import java.util.Objects;

public class Address
{
    private String street, city, region;
    private int nr;
    private int idHospital;

    public Address(String street, String city, String region, int nr)
    {
        this.street = street;
        this.city = city;
        this.region = region;
        this.nr = nr;
    }

    public Address(Address address)
    {
        setCity(address.city);
        setRegion(address.region);
        setNr(address.nr);
        setStreet(address.street);
        setIdHospital(address.idHospital);
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public int getNr()
    {
        return nr;
    }

    public void setNr(int nr)
    {
        this.nr = nr;
    }

    public int getIdHospital()
    {
        return idHospital;
    }

    public void setIdHospital(int idHospital)
    {
        this.idHospital = idHospital;
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", nr=" + nr +
                ", idHospital=" + idHospital +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return (getIdHospital() == address.getIdHospital());
    }
}

