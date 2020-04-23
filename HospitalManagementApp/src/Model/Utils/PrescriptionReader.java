package Model.Utils;

import Model.tratament.Prescription;
import Service.PrescriptionService;

public class PrescriptionReader implements ReadFile
{
    private static PrescriptionReader instance = new PrescriptionReader();
    private PrescriptionReader(){}

    public static PrescriptionReader getInstance()
    {
        return instance;
    }

    public void read(String []fields)
    {
        Prescription prescription = generateObj(fields);
        PrescriptionService.getInstance().getPrescriptionRepository().add(prescription);
    }

    public static Prescription generateObj(String []fields)
    {
        Prescription prescription = new Prescription(fields[3]);

        prescription.setCNPPatient(fields[2]);
        prescription.setCNPDoctor(fields[1]);
        prescription.setIdPrescription(Integer.parseInt(fields[0]));

        return prescription;
    }
}
