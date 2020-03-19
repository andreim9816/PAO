package ex1;

public class Ex1
{
    public static String concat(String s1 , String s2)
    {
        if((s1.substring(0, 3)).equals(s2.substring(s2.length() - 3)))
            return s1+s2;
        return s2+s1;
    }

    public static void main(String[] args)
    {
        String s = "  Sirul de caractere dat ca exemplu este acesta ";

        //1. Utilizati length, indexOf, lastIndexOf, split
        //length
        System.out.println("Lugimea sirului '" + s + "' este de " + s.length() + " caractere");

        //indexOf
        for(char i = 'A' ; i <= 'z' ; i++)
            if(s.indexOf(i) >= 0)
                System.out.println("Litera " + i + " se gaseste pe pozitia " + s.indexOf(i));

        //lastIndexOf
        for(char i = 'A' ; i <= 'z' ; i++)
            if(s.indexOf(i) >= 0 && s.lastIndexOf(i) != s.indexOf(i))
                System.out.println("Ultima pozitie a literei " + i + " se gaseste pe pozitia " + s.lastIndexOf(i));

        // split
        String arrOfStr[] = s.split(" ", s.length());
        for (String a : arrOfStr)
            System.out.println("O parte a sirului de caractere este: " + a);

        //2. Caracterul care apare de cele mai multe ori
        int []count = new int['z' + 1];
        int maxAppearance = 0;

        char maxChar = ' ';
        for (int i = 0 ; i < s.length() ; i++)
        {
            char c = s.charAt(i);
            System.out.println(c);
            count[c]++;
            if(count[c] > maxAppearance)
            {
                maxAppearance = count[c];
                maxChar = c;
            }
        }

        System.out.println("Caracterul cu cele mai multe aparitii este '" + maxChar + "' si apare de " + maxAppearance + " ori");

        // Inlocuirea lui 'a' cu '*'
        s = s.replace('a' , '*');
        System.out.println(s);

        // Stergere spatii
        String trimmed = s.trim();
        System.out.println(trimmed);

        // Concat
        String s1 = "ele fac";
        String s2 = "cafele";
        System.out.println(concat(s1,s2));
    }
}
