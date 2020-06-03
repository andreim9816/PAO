package Service;
import Model.tratament.Prescription;
import Repository.PrescriptionRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class PrescriptionService
{
    private PrescriptionRepository prescriptionRepository;
    public static PrescriptionService instance = null;

    private PrescriptionService(Connection connection)
    {
            prescriptionRepository = new PrescriptionRepository(connection);
    }

    public static PrescriptionService getInstance(Connection connection)
    {
        if(instance == null)
            instance = new PrescriptionService(connection);

        return instance;
    }

    public void add(Prescription p) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Add prescription");
        prescriptionRepository.add(p);
    }

    public ArrayList<Prescription> getPrescriptionAll() throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Get all prescriptions");
        return prescriptionRepository.getPrescriptionAll();
    }

    public Prescription getPrescriptionById(int idPrescription) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + " Get prescription by Id");
        return prescriptionRepository.getPrescriptionById(idPrescription);
    }

    public void changeRecommendation(String newRecommendation, int idPrescription) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Change prescription recommendation");
        prescriptionRepository.changeRecommendation(idPrescription, newRecommendation);
    }

    public ArrayList<Prescription> getPrescriptionFor(String cnp) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Get prescription for");
        return prescriptionRepository.getPrescriptionFor(cnp);
    }

    public ArrayList<Prescription> getPrescriptionBy(String cnp) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Get prescription By");
        return prescriptionRepository.getPrescriptionBy(cnp);
    }

    public boolean remove(int idPrescription) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Remove prescription");
        return prescriptionRepository.removeById(idPrescription);
    }
}
