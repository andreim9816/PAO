package Service;

import Comparator.ComparatorMedication;
import Main.Main;
import Model.personal.Person;
import Model.tratament.Medication;
import Model.tratament.Prescription;
import Repository.PrescriptionRepository;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class PrescriptionService
{
    private PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
    public static PrescriptionService instance = new PrescriptionService();

    private PrescriptionService(){}

    public static PrescriptionService getInstance()
    {
        return instance;
    }

    public void add(Prescription p, String CNPPatient, String CNPDoctor) throws IllegalArgumentException
    {
        AuditService.getInstance().write("Add prescription");

        if(PersonService.getInstance().isDoctor(CNPDoctor))
        {
            if(PersonService.getInstance().isPatient(CNPPatient))
            {
                p.setCNPPatient(CNPPatient);
                p.setCNPDoctor(CNPDoctor);
                prescriptionRepository.add(p);
                Main.writeFileService.appendObject(p, "prescription.csv");
//                Main.writeFileServicePrescription.appendObject(p, "prescription.csv");
//                PrescriptionCsvService.getInstance().writeFile(p);
            }
            else throw new IllegalArgumentException("No Patient found with that CNP!");
        }
        else throw new IllegalArgumentException("No Doctor found with that CNP!");
    }

    public PrescriptionRepository getPrescriptionRepository()
    {
        return prescriptionRepository;
    }

    public ArrayList<Prescription> getPrescriptionAll()
    {
        AuditService.getInstance().write("Get all prescriptions");
        return prescriptionRepository.getPrescriptionAll();
    }

    public Prescription getPrescriptionById(int idPrescription)
    {

        Prescription p = prescriptionRepository.getPrescriptionById(idPrescription);
        if(p == null)
            throw new IllegalArgumentException("No prescription found!");
        else return p;
    }

    public void changeRecommendation(String newRecommendation, int idPrescription)
    {
        AuditService.getInstance().write("Change prescription recommendation");
        Prescription p = getPrescriptionById(idPrescription);
        if(p == null)
            throw new IllegalArgumentException("Prescription does not exist!");

        p.setRecommendation(newRecommendation);
        Main.writeFileService.updateFile(getPrescriptionAll(), "pescription.csv");
//        Main.writeFileServicePrescription.updateFile(getPrescriptionAll(), "prescription.csv");
//        PrescriptionCsvService.getInstance().updateFile();
    }

    public void changeCNPDoctor(String newCNP, int idPrescription)
    {
        AuditService.getInstance().write("Change doctor CNP on prescription");
        Prescription p = getPrescriptionById(idPrescription);
        if(p == null)
            throw new IllegalArgumentException("Prescription does not exist!");

        Person doctor = PersonService.getInstance().getPersonByCNP(newCNP); // checks if person exists. If not, an error is thrown
        if(PersonService.getInstance().isDoctor(doctor.getCNP()) == false)
            throw new IllegalArgumentException("No doctor with that CNP!");

        p.setCNPDoctor(newCNP);
        Main.writeFileService.updateFile(getPrescriptionAll(), "pescription.csv");

//        Main.writeFileServicePrescription.updateFile(getPrescriptionAll(), "prescription.csv");
//        PrescriptionCsvService.getInstance().updateFile();
    }

    public void changeCNPPatient(String newCNP, int idPrescription) throws IllegalArgumentException
    {
        AuditService.getInstance().write("Change patient CNP on prescription");
        Prescription p = getPrescriptionById(idPrescription);
        if(p == null)
            throw new IllegalArgumentException("Prescription does not exist!");

        Person patient = PersonService.getInstance().getPersonByCNP(newCNP); // checks if person exists. If not, an error is thrown
        if(PersonService.getInstance().isPatient(patient.getCNP()) == false)
            throw new IllegalArgumentException("No patient with that CNP!");

        p.setCNPPatient(newCNP);
        Main.writeFileService.updateFile(getPrescriptionAll(), "pescription.csv");

//        Main.writeFileServicePrescription.updateFile(getPrescriptionAll(), "prescription.csv");
//        PrescriptionCsvService.getInstance().updateFile();
    }

    public Set<Medication> getMedicationsFromPrescription(int idPrescription)
    {
        AuditService.getInstance().write("Get medications from prescription");
        return prescriptionRepository.getMedicationsFromPrescription(idPrescription);
    }

    public Set<Medication> getAllMedications(Prescription prescription)
    {
        Set<Medication> medications = MedicationService.getInstance().getAllMedication();
        Set<Medication> result = new TreeSet<>(new ComparatorMedication());

        for(Medication m : medications)
            if(m.getIdPrescription() == prescription.getIdPrescription())
                result.add(m);
        return result;
    }

    public void remove(int idPrescription)
    {
        AuditService.getInstance().write("Remove prescription");
        Prescription p = instance.getPrescriptionById(idPrescription);
        if(p == null)
            throw  new IllegalArgumentException("Prescription does not exist!");
        else
        {
           for(Medication m : getMedicationsFromPrescription(idPrescription))
               Main.medicationService.remove(m.getIdPrescription(), m.getNameMedication());

            prescriptionRepository.removeById(idPrescription);
            Main.writeFileService.updateFile(getPrescriptionAll(), "prescription.csv");
//            Main.writeFileServicePrescription.updateFile(getPrescriptionAll(), "prescription.csv");
//            PrescriptionCsvService.getInstance().updateFile();
        }
    }
}
