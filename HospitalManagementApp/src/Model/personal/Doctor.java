package Model.personal;

public class Doctor extends Person
{
    private String grade;

    public Doctor(String lastName, String firstName, String CNP, String grade)
    {
        super(lastName, firstName, CNP);
        this.grade = grade;
    }

    public Doctor(Person p, String grade)
    {
        super(p);
        this.grade = grade;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    @Override
    public String toString()
    {
        return "Doctor{" +
                "grade='" + grade + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", CNP=" + CNP +
                ", idHospital=" + idHospital +
                ", nameDepartment='" + nameDepartment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }
}
