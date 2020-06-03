package Thread;

import Main.Main;

import java.sql.SQLException;

public class ThreadUpdateHospital extends Thread
{
    private String name;
    private int id;

    public ThreadUpdateHospital(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    @Override
    public void run()
    {
        try {
            Main.hospitalService.changeHospitalName(name, id);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
