package Main;

import Gui.MainFrame;
import Repository.DBConnectionUtil;
import Service.*;
import java.sql.Connection;
import java.sql.SQLException;
public class Main
{
    // Audit service
    public static AuditService auditService = AuditService.getInstance();
    public static Connection connectionUtil = DBConnectionUtil.getInstance();

    // Services for object handling
    public static HospitalService hospitalService = HospitalService.getInstance(connectionUtil);
    public static DepartmentService departmentService = DepartmentService.getInstance(connectionUtil);
    public static PersonService personService = PersonService.getInstance(connectionUtil);
    public static PrescriptionService prescriptionService = PrescriptionService.getInstance(connectionUtil);
//    public static MedicationService medicationService = MedicationService.getInstance();
//    public static AddressService addressService = AddressService.getInstance();

    public static void main(String[] args)
    {
        try
        {
            MainFrame mainFrame = MainFrame.getInstance("Hospital management app");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            DBConnectionUtil.closeDBConnection(connectionUtil);
        }

    }
}
