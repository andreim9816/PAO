package Model.Utils;

import Model.administrativ.Hospital;
import Service.HospitalService;

public class HospitalReader implements ReadFile
{
    private static HospitalReader instance = new HospitalReader();
    private HospitalReader(){}

    public static HospitalReader getInstance()
    {
        return instance;
    }

    public void read(String []fields)
    {
        Hospital h = generateObj(fields);
        if(h.getIdHospital() > Hospital.getNoOfObjects())
            Hospital.setNoOfObjects(h.getIdHospital());

       HospitalService.getInstance().addHospitalToRepo(h);
    }

    public static Hospital generateObj(String []fields)
    {
        return new Hospital(Integer.parseInt(fields[0]), fields[1]);
    }
}
