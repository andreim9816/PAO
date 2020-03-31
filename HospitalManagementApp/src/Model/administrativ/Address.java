package Model.administrativ;
import java.util.Objects;

public class Address
{
    private String street, city, region;
    private int nr;

    public Address()
    {
        street = city = region = "";
        nr = 0;
    }

    public Address(Address obj)
    {
        street = new String(obj.street);
        city = new String(obj.city);
        region = new String(obj.region);
        nr = obj.nr;
    }

    public Address(String street, String city, String region, int nr)
    {
        this.street = street;
        this.city = city;
        this.region = region;
        this.nr = nr;
    }

    public String getStreet()
    {
        return street;
    }

    public String getCity()
    {
        return city;
    }

    public String getRegion()
    {
        return region;
    }

    public int getNr()
    {
        return nr;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public void setNr(int nr)
    {
        this.nr = nr;
    }

    @Override
    public String toString()
    {
        return "Adresa {" +
                "street = '" + street + '\'' +
                ", city = '" + city + '\'' +
                ", region = '" + region + '\'' +
                ", nr = " + nr +
                " } ";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getNr() == address.getNr() &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getRegion(), address.getRegion());
    }
}

