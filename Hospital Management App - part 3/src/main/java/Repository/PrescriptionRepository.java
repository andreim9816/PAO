package Repository;
import Comparator.ComparatorMedication;
import Main.Main;
import Model.administrativ.Hospital;
import Model.tratament.Medication;
import Model.tratament.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrescriptionRepository
{
    private Connection connection;

    public PrescriptionRepository(Connection connection)
    {
        this.connection = connection;

    }

    public void add(Prescription r) throws SQLException
    {
        String query = "INSERT INTO prescription VALUES(?, ?, ?, ?)";

        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, 0); // se autoincrementeaza singur id-ul
        statement.setString(2, r.getCNPPatient());
        statement.setString(3, r.getCNPDoctor());
        statement.setString(4, r.getRecommendation());
        statement.executeUpdate();
    }

    public Prescription getPrescriptionById(int idPrescription) throws SQLException
    {
        String query = "SELECT * FROM prescription WHERE idprescription = ?";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, idPrescription);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            String cnpDoctor = resultSet.getString("cnp_doctor");
            String cnpPatient = resultSet.getString("cnp_patient");
            String recommendation = resultSet.getString("recommendation");
            return new Prescription(idPrescription, cnpPatient, cnpDoctor, recommendation);
        }
        return null;
    }

    public void changeRecommendation(int id, String recommendation) throws SQLException
    {
        String query = "UPDATE prescription " +
                       "SET recommendation = ? " +
                       "WHERE idprescription = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(2, id);
        statement.setString(1, recommendation);
        statement.executeUpdate();
    }

    public ArrayList<Prescription> getPrescriptionAll() throws SQLException
    {
        ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
        String query = "SELECT * FROM prescription";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next())
        {
            int id = resultSet.getInt("idprescription");
            String cnpDoctor = resultSet.getString("cnp_doctor");
            String cnpPatient = resultSet.getString("cnp_patient");
            String recommendation = resultSet.getString("recommendation");
            Prescription p = new Prescription(id, cnpPatient, cnpDoctor, recommendation);
            prescriptions.add(p);
        }
        return prescriptions;
    }

    public ArrayList<Prescription> getPrescriptionBy(String cnp) throws SQLException
    {
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        String query = "SELECT * FROM prescription WHERE cnp_doctor = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, cnp);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next())
        {
            int id = resultSet.getInt("idprescription");
            String cnpDoctor = resultSet.getString("cnp_doctor");
            String cnpPatient = resultSet.getString("cnp_patient");
            String recommendation = resultSet.getString("recommendation");
            Prescription p = new Prescription(id, cnpPatient, cnpDoctor, recommendation);
            prescriptions.add(p);
        }
        return prescriptions;
    }

    public ArrayList<Prescription> getPrescriptionFor(String cnp) throws SQLException
    {
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        String query = "SELECT * FROM prescription WHERE cnp_patient = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, cnp);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next())
        {
            int id = resultSet.getInt("idprescription");
            String cnpDoctor = resultSet.getString("cnp_doctor");
            String cnpPatient = resultSet.getString("cnp_patient");
            String recommendation = resultSet.getString("recommendation");
            Prescription p = new Prescription(id, cnpPatient, cnpDoctor, recommendation);
            prescriptions.add(p);
        }
        return prescriptions;
    }

    public boolean removeById(int ID) throws SQLException
    {
        String query = "DELETE FROM prescription WHERE idprescription = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, ID);
        return statement.executeUpdate() > 0;
    }
}
