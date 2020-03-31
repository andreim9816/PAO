package Model.administrativ;

import java.lang.reflect.Array;
import java.util.Vector;
import java.util.Objects;
import java.util.Vector;

public class Hospital
{
    private Address address;
    private String name;
    private Vector<Department> arrayDepartment;

    public Hospital()
    {
        name = "";
        address = new Address();
        arrayDepartment = new Vector<>();
    }

    public Hospital(Address address)
    {
        name = "";
        this.address = new Address(address);
        arrayDepartment = new Vector<>();
    }

    public Hospital(Address adr , String name)
    {
        this.address = adr;
        this.name = name;
        arrayDepartment = new Vector<>();

    }

    public Hospital(Address address, String name , Vector<Department> arrayDepartment)
    {
        this.name = name;
        this.address = address;
        this.arrayDepartment = arrayDepartment;
    }

    public Address getAddress()
    {
        return address;
    }

    public Vector<Department> getArrayDepartment()
    {
        return arrayDepartment;
    }

    public String getName()
    {
        return name;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setArrayDepartment(Vector<Department> arrayDepartment)
    {
        this.arrayDepartment = arrayDepartment;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return Objects.equals(getAddress(), hospital.getAddress()) &&
                Objects.equals(getName(), hospital.getName()) &&
                Objects.equals(getArrayDepartment(), hospital.getArrayDepartment());
    }

    @Override
    public String toString() {
        return "Spital { " +
                "adresa = " + address +
                ", name = '" + name + '\'' +
                ", arrayDepartment = " + arrayDepartment +
                " } ";
    }

    public static void main(String[] args)
    {
        Hospital h = new Hospital(new Address("Cuza Voda" , "Roman" , "Neamt" , 10));
        Hospital h1 = new Hospital(new Address("Tineretului" , "Piatra Neamt" , "Neamt" , 10));

        Department s = new Department("Cardiologie"  ,10);
        Department s1 = new Department("Neurologie"  , 26);

        System.out.println(h);
        /**
         * h.print();
         * h1.print();
         */

        System.out.println("----------------");

        Vector<Department>vectDepartment  = h.getArrayDepartment();
        vectDepartment.get(0).setName("Andrei");

        /**
         *
         * h.print();
         *         h1.print();
         */

    }
}

