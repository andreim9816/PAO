package Repository;

import Model.administrativ.Address;
import java.util.ArrayList;

public class AddressRepository
{
    private ArrayList<Address> addressDB;

    public AddressRepository()
    {
        addressDB = new ArrayList<>();
    }

    public void add(Address adr)
    {
        addressDB.add(adr);
    }

    public ArrayList<Address> getAllAddress()
    {
        return addressDB;
    }

    public Address getAddressReference(Address D)
    {
        for(Address d: addressDB)
            if(d.getIdHospital() == D.getIdHospital())
                return d;
        return null;
    }

    public Address getAddressByIdHospital(int idHospital)
    {
        for(Address a : addressDB)
            if(a.getIdHospital() == idHospital)
                return a;
        return null;
    }

    public void remove(Address adr)
    {
        addressDB.remove(adr);
    }
}
