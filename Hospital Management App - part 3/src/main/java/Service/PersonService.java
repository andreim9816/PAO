package Service;

import Main.Main;
import Model.personal.Person;
import Repository.PersonRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonService
{
    private PersonRepository personRepository;
    public static PersonService instance = null;

    private PersonService(Connection connection)
    {
        personRepository = new PersonRepository(connection);
    }

    public static PersonService getInstance(Connection connection)
    {
        if(instance == null)
            instance = new PersonService(connection);

        return instance;
    }

    public void add(Person p, int idHospital, String nameDepartment) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Add person");
        p.setIdHospital(idHospital);
        p.setNameDepartment(nameDepartment);
        personRepository.add(p);
    }

    public Person getPersonByCNP(String CNP) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Get person by CNP");
        return personRepository.getPersonByCNP(CNP);
    }

    public ArrayList<Person> getAllPerson() throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Get all persons");
       return personRepository.getAllPerson();
    }

    public ArrayList<Person> getAllPatientsFromDepartment(int idHospital, String departmentName) throws SQLException
    {
        Main.auditService.write(Thread.currentThread().getName() + ", Get all Patients From Department");
        return personRepository.getAllPatientsFromDepartment(idHospital, departmentName);
    }

    public ArrayList<Person> getAllNursesFromDepartment(int idHospital, String departmentName) throws SQLException
    {
        Main.auditService.write(Thread.currentThread().getName() + ", Get all Nurses From Department");
        return personRepository.getAllNursesFromDepartment(idHospital, departmentName);
    }

    public ArrayList<Person> getAllDoctorsFromDepartment(int idHospital, String departmentName) throws SQLException
    {
        Main.auditService.write(Thread.currentThread().getName() + ", Get all Doctors From Department");
        return personRepository.getAllDoctorsFromDepartment(idHospital, departmentName);
    }

    public boolean changeFirstName(String cnp, String name) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Change first name");
        return personRepository.changeFirstName(cnp, name);
    }

    public boolean changeLastName(String cnp, String name) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Change last name");
        return personRepository.changeLastName(cnp, name);
    }

    public boolean remove(String CNP) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Remove person");
        return personRepository.remove(CNP);
    }
}
