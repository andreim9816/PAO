package Repository;
import Model.administrativ.Department;
import Model.administrativ.Hospital;
import Model.personal.Doctor;
import Model.personal.Nurse;
import Model.personal.Patient;
import Model.personal.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentRepository
{
    private Connection connection;

    public DepartmentRepository(Connection connection)
    {
        this.connection = connection;
    }

    public void add(Department d) throws SQLException
    {
        String query = "INSERT INTO department VALUES(?, ?, ?)";

        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, d.getIdHospital());
        statement.setString(2, d.getNameDepartment());
        statement.setInt(3, d.getNoOfBeds());
        statement.executeUpdate();
    }

    public ArrayList<Department> getDepartmentAll() throws SQLException
    {
        ArrayList<Department> departments = new ArrayList<Department>();

        String query = "SELECT * FROM department";
        PreparedStatement statement =  connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int beds = resultSet.getInt(3);

            Department d = new Department(name, beds);
            d.setIdHospital(id);
            departments.add(d);
        }
        return departments;
    }

    public ArrayList<Person> getAllPersonsFromDepartment(int id, String name) throws SQLException
    {
        ArrayList<Person> persons = new ArrayList<Person>();

        String query = "SELECT p.idhospital, p.name_department, p.last_name, p.first_name, p.cnp, p.type, p.caract " +
                       "FROM department d " +
                       "JOIN person p ON (d.idhospital = p.idhospital AND d.name_department = p.name_department) " +
                       "WHERE d.name_department = ? AND d.idhospital = ?";

        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setInt(2, id);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            int idHospital = resultSet.getInt(1);
            String nameDep = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String firstName = resultSet.getString(4);
            String cnp = resultSet.getString(5);
            String type = resultSet.getString(6);
            String caract = resultSet.getString(7);

            Person p;
            if(type.equals("doctor"))
                p = new Doctor(lastName, firstName, cnp, caract);
            else if(type.equals("pacient"))
                p = new Patient(lastName, firstName, cnp, Boolean.parseBoolean(caract));
            else p = new Nurse(lastName, firstName, cnp);

            p.setNameDepartment(nameDep);
            p.setIdHospital(idHospital);

            persons.add(p);
        }
        return persons;
    }

    public Department getDepartment(int idHospital, String name) throws SQLException
    {
        String query = "SELECT * FROM department WHERE idhospital = ? AND name_department = ?";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, idHospital);
        statement.setString(2, name);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            Department d = new Department(resultSet.getString(2), resultSet.getInt(3));
            d.setIdHospital(resultSet.getInt(1));
            return d;
        }
        return null;
    }

    public void changeDepartmentName(int idHospital, String name, String newName) throws SQLException
    {
        String query = "UPDATE department " +
                "SET name_department = ? " +
                "WHERE idhospital = ? AND name_department = ?";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setString(1, newName);
        statement.setInt(2, idHospital);
        statement.setString(3, name);
        statement.executeUpdate();
    }

    public boolean changeNoOfBeds(int idHospital, String name, int newNumber) throws SQLException
    {
        String query = "UPDATE department " +
                       "SET no_of_beds = ? " +
                       "WHERE idhospital = ? AND name_department = ?";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, newNumber);
        statement.setInt(2, idHospital);
        statement.setString(3, name);
        return statement.executeUpdate() > 0;
    }

    public boolean remove(int id, String name) throws  SQLException
    {
        String query = "DELETE FROM department WHERE idhospital = ? AND name_department = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.setString(2, name);
        return statement.executeUpdate() > 0;
    }
}
