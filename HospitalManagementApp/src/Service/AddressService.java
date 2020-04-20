package Service;

import Model.administrativ.Address;
import Repository.AddressRepository;

import java.util.ArrayList;

public class AddressService
{
    private AddressRepository addressRepository = new AddressRepository();
    private static AddressService instance = new AddressService();

    private AddressService(){}

    public static AddressService getInstance()
    {
        return instance;
    }

    public void addAddress(Address address, int idHospital)
    {
        address.setIdHospital(idHospital);
        addressRepository.add(address);
    }

    public Address getHospitalAddress(int idHospital)
    {
        Address adr = addressRepository.getAddressByIdHospital(idHospital);
        if(adr == null)
            throw new IllegalArgumentException("No address found!");
        else return adr;
    }

    public void changeStreet(String newStreetName, int idHospital)
    {
        Address address = addressRepository.getAddressByIdHospital(idHospital);
        if(address == null)
            throw new IllegalArgumentException("No address found!");

        address.setStreet(newStreetName);
    }

    public void changeCity(String newCityName, int idHospital)
    {
        Address address = addressRepository.getAddressByIdHospital(idHospital);
        if(address == null)
            throw new IllegalArgumentException("No address found!");

        address.setCity(newCityName);
    }

    public void changeNumber(int newNumber, int idHospital)
    {
        Address address = addressRepository.getAddressByIdHospital(idHospital);
        if(address == null)
            throw new IllegalArgumentException("No address found!");

        address.setNr(newNumber);
    }

    public void changeRegion(String newRegionName, int idHospital)
    {
        Address address = addressRepository.getAddressByIdHospital(idHospital);
        if(address == null)
            throw new IllegalArgumentException("No address found!");

        address.setRegion(newRegionName);
    }

    public ArrayList<Address> getAllAddress()
    {
        return addressRepository.getAllAddress();
    }
}
