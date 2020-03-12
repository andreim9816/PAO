package probleme12345;

import java.util.Scanner;

public class MatriceSimetrica
{
    private int [][]matrix;
    private int m, n;

    public void readMatrix()
    {
        Scanner scanner = new Scanner(System.in);

        m = scanner.nextInt();
        n = scanner.nextInt();

        matrix = new int[m][n];

        for(int i = 0 ; i < m ; i++)
            for(int j = 0 ; j < n ; j++)
                matrix[i][j] = scanner.nextInt();
    }

    public boolean isSimetric()
    {
        int i , j;

        for(i = 0 ; i < m ; i++ )
            for(j = i ; j < n ; j++ )
                if(matrix[i][j] != matrix[j][i])
                    return false;
         return true;
    }

    public static void main(String[] args)
    {
        MatriceSimetrica m = new MatriceSimetrica();
        m.readMatrix();
        if(m.isSimetric())
            System.out.println("Este simetrica");
        else System.out.println("Nu este simetrica");
    }
}
