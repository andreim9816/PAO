package probleme12345;

import java.util.Scanner;

public class Matrix
{
    private int [][]matrix;
    private int lin;
    private int col;

    public Matrix()
    {
        lin = 0 ;
        col = 0 ;
        matrix = new int[0][0];
    }

    public Matrix(int m, int n)
    {
        lin = m;
        col = n;
        matrix = new int[m][n];

    }

    public void readMatrix()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Numarul de linii: ");
        lin = scanner.nextInt();

        System.out.print("Numarul de coloane: ");
        col = scanner.nextInt();

        matrix = new int[lin][col];

        System.out.println("Elementele matricei: ");
        for(int i = 0 ; i < lin ; i++)
            for(int j = 0 ; j < col ; j++)
                matrix[i][j] = scanner.nextInt();
    }


    public Matrix add(Matrix m2)
    {
        int i, j;
        Matrix sum = new Matrix(lin , col);

        if(!(lin == m2.lin && col == m2.col))
            throw new ArithmeticException("The matrixes don't have the same size");

        for(i = 0 ; i < lin ; i++)
            for(j = 0 ; j < col ; j++)
                sum.matrix[i][j] = matrix[i][j] + m2.matrix[i][j];

        return sum;
    }

    public Matrix multiply(Matrix m2)
    {
        if(col != m2.lin)
            throw new ArithmeticException("The sizes dont match ");

        Matrix mult = new Matrix(lin, m2.col);
        int i ,j , k;

        for(i = 0 ; i < mult.lin ; i++)
            for(j = 0 ; j < mult.col ; j++)
                for(k = 0 ; k < col ; k++)
                    mult.matrix[i][j] += matrix[i][k] * m2.matrix[k][j];



        return mult;
    }

    public Matrix power(int power)
    {

        if(power < 0)
            throw new ArithmeticException("Power should be positive!");
        int i, j, p;
        Matrix result = new Matrix(lin , col);

        // constructing the Identity matrix
        for(i = 0 ; i < lin ; i++)
            result.matrix[i][i] = 1;

        for(p = 1 ; p <= power ; p++)
        {
            result = result.multiply(this);
        }
        return result;
    }

    @Override
    public String toString()
    {
        String result = new String();
        result = "Linii: " + lin + " coloane: " + col + "\n";

        int i, j;

        for(i = 0 ; i < lin ; i++)
        {
            for(j = 0 ; j < col ; j++)
               result = result + matrix[i][j] + " ";
            result += "\n";
        }

        return result;
    }

    public static void main(String[] args)
    {
            Matrix m1 = new Matrix();
            Matrix m2 = new Matrix();

            m1.readMatrix();
            m2.readMatrix();

            Matrix sum = m1.add(m2);
            System.out.println(sum.toString());

            Matrix prod = m1.multiply(m2);
            System.out.println(prod.toString());

            Matrix ofPower = m1.power(5);
            System.out.println(ofPower.toString());
    }

}
