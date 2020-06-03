package Service;

import Model.tratament.Medication;
import Repository.MedicationRepository;

import java.util.Set;

public class MedicationService
{
    private MedicationRepository medicationRepository = new MedicationRepository();
    public static MedicationService instance = new MedicationService();

    private MedicationService(){}

    public static MedicationService getInstance()
    {
        return instance;
    }

    public void add(Medication m, int idPrescription)
    {
        m.setIdPrescription(idPrescription);
        medicationRepository.add(m);
    }

    public Medication getMedicationByIdPrescriptionAndName(int idPrescription, String nameMedication)
    {
        return medicationRepository.getMedicationByIdPrescriptionAndName(idPrescription, nameMedication);
    }

    public Set<Medication> getAllMedication()
    {
        return medicationRepository.getAllMedication();
    }

    public void changeQuantity(int newQuantity, int idPrescription, String nameMedication)
    {
        Medication m = getMedicationByIdPrescriptionAndName(idPrescription, nameMedication);
        if(m == null)
            throw new IllegalArgumentException("Medication does not exist!");
        m.setQuantity(newQuantity);
    }

    public void remove(int idPrescription, String nameMedication)
    {
       Medication m = instance.getMedicationByIdPrescriptionAndName(idPrescription, nameMedication);
       if(m == null)
           throw new IllegalArgumentException("No medication was deleted because it was not found");
       else medicationRepository.remove(m);
    }
}
