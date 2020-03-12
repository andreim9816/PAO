package scoala;

public class Student
{
    private String nume;
    private double medieSem1;
    private double medieSem2;

    public Student()
    {
        nume = "";
        medieSem2 = medieSem1 = 0.0;
    }

    public Student(String name , double m1, double m2)
    {
        nume = name;
        medieSem2 = m2;
        medieSem1 = m1;
    }
    @Override
    public String toString()
    {
        return " Student: " + nume + " medie sem1: " + medieSem1 + " | medie sem2: " + medieSem2;
    }

    public double getMedieAn1()
    {
        return (medieSem1 + medieSem2) /2.0;
    }
}
