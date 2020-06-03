package Repository;
import Model.administrativ.Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HospitalRepository
{
    private Connection connection;

    public HospitalRepository(Connection connection)
    {
        this.connection = connection;
    }

    synchronized public boolean addHospital(String name) throws SQLException
    {

            String query = "INSERT INTO hospital VALUES(?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 0);
            statement.setString(2, name);
            statement.executeUpdate();
            return true;
    }

    public ArrayList<Hospital> getAllHospital() throws SQLException
    {
        ArrayList<Hospital> hospitals = new ArrayList<>();

        String query = "SELECT * FROM hospital";
        PreparedStatement statement =  connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Hospital h = new Hospital(id, name);
            hospitals.add(h);
        }
        return hospitals;
    }

    synchronized public boolean changeHospitalName(int id, String newName) throws SQLException
    {
        String query = "UPDATE hospital SET name = ? WHERE idhospital = ?";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setString(1, newName);
        statement.setInt(2, id);
        boolean count = statement.executeUpdate() > 0;
//        System.out.println(count);
        return count;
    }

    public Hospital getHospitalById(int id) throws SQLException
    {
        String query = "SELECT * FROM hospital WHERE idhospital = ?";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            return new Hospital(resultSet.getInt(1), resultSet.getString(2));
        }
        return null;
    }

    public boolean remove(int id) throws  SQLException
    {
        String query = "DELETE FROM hospital WHERE idhospital = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        int count = statement.executeUpdate();
        return count > 0;
    }
}

