package Repository;

import Model.administrativ.Department;
import Model.personal.Doctor;
import Model.personal.Nurse;
import Model.personal.Patient;
import Model.personal.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class PersonRepository
{
    private Connection connection;

    public PersonRepository(Connection connection)
    {
        this.connection = connection;
    }

    public void add(Person p) throws SQLException
    {
        String query = "INSERT INTO person(cnp, idhospital, name_department, last_name, first_name, type, caract) " +
                       "VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement =  connection.prepareStatement(query);

        statement.setString(1, p.getCNP());
        statement.setInt(2, p.getIdHospital());
        statement.setString(3, p.getNameDepartment());
        statement.setString(4, p.getLastName());
        statement.setString(5, p.getFirstName());

        if(p instanceof Patient)
        {
            statement.setString(6, "pacient");
            statement.setString(7, String.valueOf(((Patient) p).getInsured()));
        }
        else if (p instanceof Nurse)
        {
            statement.setString(6, "asistenta");
            statement.setNull(7,Types.BOOLEAN);

        }
        else
        {
            statement.setString(6, "doctor");
            statement.setString(7, ((Doctor) p).getGrade());
        }
        statement.executeUpdate();
    }

    public Person getPersonByCNP(String cnp) throws SQLException
    {
        String query = "SELECT *" +
                       "FROM person " +
                       "WHERE cnp = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, cnp);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            Person p;

            String type = resultSet.getString(6);
            int id = resultSet.getInt(2);
            String nameDep = resultSet.getString(3);
            String lastName = resultSet.getString(4);
            String firstName = resultSet.getString(5);

            if(type.equals("doctor"))
                p = new Doctor(lastName, firstName, cnp, resultSet.getString(7), id, nameDep);
            else if (type.equals("asistenta"))
                p = new Nurse(lastName, firstName, cnp, id, nameDep);
            else
                p = new Patient(lastName, firstName, cnp, Boolean.parseBoolean(resultSet.getString(7)), id, nameDep);

            return p;
        }
        return null;
    }

    public ArrayList<Person> getAllPerson() throws SQLException
    {
        ArrayList<Person> persons = new ArrayList<>();

        String query = "SELECT * FROM person";
        PreparedStatement statement =  connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
           Person p;

           String type = resultSet.getString(6);
           String cnp = resultSet.getString(1);
           int id = resultSet.getInt(2);
           String nameDep = resultSet.getString(3);
           String lastName = resultSet.getString(4);
           String firstName = resultSet.getString(5);

           if(type.equals("doctor"))
               p = new Doctor(lastName, firstName, cnp, resultSet.getString(7), id, nameDep);
           else if (type.equals("asistenta"))
               p = new Nurse(lastName, firstName, cnp, id, nameDep);
           else
               p = new Patient(lastName, firstName, cnp, Boolean.parseBoolean(resultSet.getString(7)), id, nameDep);

           persons.add(p);
        }
        return persons;
    }

    public ArrayList<Person> getAllPersonFromDepartment(int idHospital, String nameDepartment) throws SQLException
    {
        ArrayList<Person> persons = new ArrayList<>();

        String query = "SELECT * FROM person WHERE idhospital = ? AND name_department = ?";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, idHospital);
        statement.setString(2, nameDepartment);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            Person p;

            String type = resultSet.getString(6);
            String cnp = resultSet.getString(1);
            String lastName = resultSet.getString(4);
            String firstName = resultSet.getString(5);

            if(type.equals("doctor"))
                p = new Doctor(lastName, firstName, cnp, resultSet.getString(7), idHospital, nameDepartment);
            else if (type.equals("asistenta"))
                p = new Nurse(lastName, firstName, cnp, idHospital, nameDepartment);
            else
                p = new Patient(lastName, firstName, cnp, Boolean.parseBoolean(resultSet.getString(7)), idHospital, nameDepartment);

            persons.add(p);
        }
        return persons;
    }

    public ArrayList<Person> getAllDoctorsFromDepartment(int idHospital, String nameDepartment) throws SQLException
    {
        ArrayList<Person> persons = new ArrayList<>();

        String query = "SELECT * FROM person WHERE idhospital = ? AND name_department = ? AND type = ?";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, idHospital);
        statement.setString(2, nameDepartment);
        statement.setString(3, "doctor");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            Person p;

            String cnp = resultSet.getString(1);
            String lastName = resultSet.getString(4);
            String firstName = resultSet.getString(5);
            String grade = resultSet.getString("caract");

            p = new Doctor(lastName, firstName, cnp, grade, idHospital, nameDepartment);
            persons.add(p);
        }
        return persons;
    }

    public ArrayList<Person> getAllPatientsFromDepartment(int idHospital, String nameDepartment) throws SQLException
    {

        ArrayList<Person> persons = new ArrayList<>();

        String query = "SELECT * FROM person WHERE idhospital = ? AND name_department = ? AND type = ?";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, idHospital);
        statement.setString(2, nameDepartment);
        statement.setString(3, "pacient");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            Person p;

            String cnp = resultSet.getString(1);
            String lastName = resultSet.getString(4);
            String firstName = resultSet.getString(5);
            boolean info = resultSet.getBoolean("caract");

            p = new Patient(lastName, firstName, cnp, info, idHospital, nameDepartment);
            persons.add(p);
        }
        return persons;
    }

    public ArrayList<Person> getAllNursesFromDepartment(int idHospital, String nameDepartment) throws SQLException
    {

        ArrayList<Person> persons = new ArrayList<>();

        String query = "SELECT * FROM person WHERE idhospital = ? AND name_department = ? AND type = ?";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, idHospital);
        statement.setString(2, nameDepartment);
        statement.setString(3, "nurse");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            Person p;

            String cnp = resultSet.getString(1);
            String lastName = resultSet.getString(4);
            String firstName = resultSet.getString(5);

            p = new Nurse(lastName, firstName, cnp, idHospital, nameDepartment);
            persons.add(p);
        }
        return persons;
    }

    public boolean changeFirstName(String cnp, String name) throws SQLException
    {
        String query = "UPDATE person SET first_name = ? WHERE cnp = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, cnp);
        return statement.executeUpdate() > 0;
    }

    public boolean changeLastName(String cnp, String name) throws SQLException
    {
        String query = "UPDATE person SET last_name = ? WHERE cnp = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, cnp);
        return statement.executeUpdate() > 0;
    }

    public boolean remove(String cnp) throws SQLException
    {
        String query = "DELETE FROM person WHERE cnp = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, cnp);
        return statement.executeUpdate() > 0;
    }
}
