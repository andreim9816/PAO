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

    public ArrayList<Address> getAll()
    {
        return addressDB;
    }

    public Address getAddressReference(Address D)
    {
        /**
         * Function that returns a reference to the specified object
         */

        for(Address d: addressDB)
            if(d.equals(D))
                return d;
        return null;
    }
    public void remove(Address adr)
    {
        /**
         * Function that removes a specific Address
         */

        addressDB.remove(adr);
    }
}
