package Thread;

import Main.Main;

import java.sql.SQLException;

public class ThreadAddHospital extends Thread
{
    private String name;

    public ThreadAddHospital(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        try {
            Main.hospitalService.addHospital(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
