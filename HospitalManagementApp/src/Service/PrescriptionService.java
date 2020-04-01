package Service;

import Main.Main;
import Model.tratament.Medication;
import Model.tratament.Prescription;
import Repository.PrescriptionRepository;

import java.util.ArrayList;
import java.util.Vector;

public class PrescriptionService
{
    private PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
    public static PrescriptionService instance = new PrescriptionService();

    private PrescriptionService(){}

    public static PrescriptionService getInstance()
    {
        return instance;
    }

    public void add(Prescription p)
    {
        prescriptionRepository.add(p);
    }

    public ArrayList<Prescription> getPrescriptionAll()
    {
        return prescriptionRepository.getPrescriptionAll();
    }

    public void addPrescriptionToRepository(Prescription p)
    {
        /**
         * Adds prescription
         */
        ArrayList<Prescription> prescriptionDB = Main.prescriptionService.getPrescriptionAll();
        prescriptionDB.add(p);
    }

    public void addMedicationToPrescription(Prescription P , Medication M)
    {
        /**
         * Function that adds a Medication to a Prescription
         */
        Main.medicationService.add(M); // adds Medication M
        prescriptionRepository.getPrescriptionReference(P).getArrayMedication().add(M); // adds Medication to the object's field
    }

    public void remove(Prescription p)
    {
        // removes Prescription and all its Medication inside it
        Vector<Medication> arrayMedication = p.getArrayMedication();

        for(int i = 0 ; i < arrayMedication.size() ; i++)
        {
            System.out.println(arrayMedication.get(i));
            Main.medicationService.remove(arrayMedication.get(i));
            arrayMedication.remove(i);
            i--;
        }
        prescriptionRepository.remove(p);
        p.setRecommendation("");
    }

}
