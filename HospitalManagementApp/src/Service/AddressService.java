package Service;

import Model.administrativ.Address;
import Repository.AddressRepository;

public class AddressService
{
    private AddressRepository addressRepository;

    private static AddressService instance = new AddressService();

    private AddressService(){}

    public static AddressService getInstance()
    {
        return instance;
    }

    public void removeAddress(Address d)
    {
        /**
         * Function that removes a specified Address
         */

        addressRepository.remove(d);
    }

    public void changeAddressStreet(Address D , String street)
    {
        /**
         * Function that changes a specified Address' street
         */
        Address adr = addressRepository.getAddressReference(D); // gets a reference to the Address D in the Repository
        adr.setStreet(street);
    }

    public void changeAddressCity(Address D , String city)
    {
        /**
         * Function that changes a specified Address' city
         */
        Address adr = addressRepository.getAddressReference(D); // gets a reference to the Address D in the Repository
        adr.setCity(city);
    }

    public void changeAddressRegion(Address D , String region)
    {
        /**
         * Function that changes a specified Address' region
         */
        Address adr = addressRepository.getAddressReference(D); // gets a reference to the Address D in the Repository
        adr.setRegion(region);
    }

    public void changeAddressNumber(Address D , int number)
    {
        /**
         * Function that changes a specified Address' number
         */

        Address adr = addressRepository.getAddressReference(D); // gets a reference to the Address D in the Repository
        adr.setNr(number);
    }
}
