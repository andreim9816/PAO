package Repository;
import Model.administrativ.Hospital;
import java.util.ArrayList;

public class HospitalRepository
{
    private ArrayList<Hospital> hospitalDB;

    public HospitalRepository()
    {
        hospitalDB = new ArrayList<>();
    }

    public void add(Hospital h)
    {
        hospitalDB.add(h);
    }

    public ArrayList<Hospital> getAllHospital()
    {
        return hospitalDB;
    }

    public Hospital getHospitalById(int id)
    {
        for(Hospital h : hospitalDB)
            if(id == h.getIdHospital())
                return h;
        return null;
    }

    public void remove(Hospital h)
    {
        hospitalDB.remove(h);
    }
}

