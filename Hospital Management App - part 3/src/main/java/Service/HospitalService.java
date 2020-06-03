package Service;
import Main.Main;
import Model.administrativ.Hospital;
import Model.personal.Person;
import Repository.HospitalRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class HospitalService
{
    private HospitalRepository hospitalRepository;
    public static HospitalService instance = null;

    private HospitalService(Connection connection)
    {
        hospitalRepository = new HospitalRepository(connection);
    }

    public static HospitalService getInstance(Connection connection)
    {
        if(instance == null)
            instance = new HospitalService(connection);
        return instance;
    }

    public boolean addHospital(String name) throws SQLException
    {
        String actionName = Thread.currentThread().getName() + ", Add hospital";
        Main.auditService.write(actionName);
        return hospitalRepository.addHospital(name);
    }

    public boolean changeHospitalName(String newName, int id) throws SQLException
    {
        String actionName = Thread.currentThread().getName() + ", ChangeHospitalName";
        Main.auditService.write(actionName);
        return hospitalRepository.changeHospitalName(id, newName);
    }

    public Hospital getHospitalById(int id) throws SQLException
    {
        Main.auditService.write(Thread.currentThread().getName() + ", Get an Hospital by ID");
        return hospitalRepository.getHospitalById(id);
    }

    public ArrayList<Hospital> getAllHospital() throws SQLException
    {
        Main.auditService.write(Thread.currentThread().getName() + ", Get all Hospitals");
        return hospitalRepository.getAllHospital();
    }

    public boolean remove(int idHospital) throws SQLException
    {
        Main.auditService.write(Thread.currentThread().getName() + ", Remove an Hospital");
        return hospitalRepository.remove(idHospital);
    }

}
