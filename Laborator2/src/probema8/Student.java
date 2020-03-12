package probema8;

import java.util.Vector;

public class Student
{
    private String nume;
    private String prenume;

    public static int contor = 0;


    public Student()
    {
        nume = prenume = "";
        contor++;
    }

    public Student(String nume1, String nume2)
    {
        contor++;
        nume = nume1;
        prenume = nume2;
    }

    @Override
    public String toString()
    {
        return  "Ma numesc " + nume + " " + prenume;
    }

    public static void main(String[] args)
    {

     Vector<Student>v = new Vector<Student>();

     Student s1 =new Student("Andrei", "Mano");
     Student s2 =new Student("Gheorghe" , "Hagi");
     Student s3 =new Student("Vlad" , "Duncea");
     Student s4 =new Student("Jack" , "Sparrow");
     Student s5 =new Student("Bruce" , "Wayne");

    v.add(s1);
    v.add(s2);
    v.add(s3);
    v.add(s4);
    v.add(s5);

    for(Student i : v)
        System.out.println(i.toString());

    System.out.println("Contor = " + Student.contor);
    }
}


