package Service;

import Model.tratament.Dosage;
import Repository.DosageRepository;

public class DosageService
{
    private DosageRepository dosageRepository = new DosageRepository();
    private static DosageService instance = new DosageService();

    private DosageService(){}

    public static DosageService getInstance()
    {
        return instance;
    }

    public void add(Dosage D)
    {
        dosageRepository.add(D);
    }

    public void changeQuantity(Dosage D , int newQuantity)
    {
        dosageRepository.getDosageReference(D).setQuantity(newQuantity);
    }

    public void changeSpecs(Dosage D , String newSpecs)
    {
        dosageRepository.getDosageReference(D).setspecs(newSpecs);
    }

    public void remove(Dosage D)
    {
        dosageRepository.remove(D);
    }


}
