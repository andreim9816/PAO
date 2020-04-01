package Model.personal;

import java.util.Objects;

public class Doctor extends Person
{
    private String grade;

    public Doctor()
    {
        super();
        grade = "";
    }

    public Doctor(String lastName , String firstName , String grade)
    {
        super(lastName , firstName);
        this.grade = grade;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String gr)
    {
        grade = new String(gr);
    }

    @Override
    public String toString()
    {
        return "Medic { " +
                "grade = '" + grade + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", firstName = '" + firstName + '\'' +
                " } ";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals((Person)o)) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(getGrade(), doctor.getGrade());
    }

}
