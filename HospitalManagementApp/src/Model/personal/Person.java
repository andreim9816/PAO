package Model.personal;
import java.util.Objects;

abstract public class Person
{
    protected String lastName;
    protected String firstName;
    private int ID;
    static int noOfObjects = 0;
    {
        noOfObjects++;
    }

    public Person()
    {
        lastName = firstName = "";
        ID = noOfObjects;
    }

    public Person(String lastName , String firstName)
    {
        setLastName(lastName);
        setFirstName(firstName);
        ID = noOfObjects;
    }

    public String getlastName()
    {
        return lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public int getID()
    {
        return ID;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setPerson(String lastName,  String firstName)
    {
        setLastName(lastName);
        setFirstName(firstName);
        ID = noOfObjects;
    }

    @Override
    public String toString()
    {
        return "Person { " +
                "lastName = " + lastName + '\'' +
                ", firstName = '" + firstName + '\'' +
                " } ";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getlastName(), person.getlastName()) &&
                Objects.equals(getFirstName(), person.getFirstName());
    }
}
