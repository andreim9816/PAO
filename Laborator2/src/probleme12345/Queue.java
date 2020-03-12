package probleme12345;

public class Queue
{
    private int array[];
    private int size;

    public Queue()
    {
        size = 0;
        array = new int[0];
    }

    public Queue(int size)
    {
        this.size = size;
        array = new int[size];
    }

    public void push(int elem)
    {
        int aux[] = new int[size];

        for(int i = 0 ; i < size ; i++ )
            aux[i] = array[i];

        size++;
        array = new int[size];

        for(int i = 0 ; i < size - 1; i++ )
            array[i] = aux[i];

        array[size - 1] = elem;
    }

    public int pop()
    {
        int i , aux[] = new int[size];
        int elem = array[0];

        for(i = 1 ; i < size ; i++ )
            aux[i] = array[i];

        size--;

        array = new int[size];

        for(i = 0 ; i < size ; i++)
            array[i] = aux[i + 1];

        return elem;
    }

    public boolean isEmpty()
    {
        return (size == 0);
    }

    public String toString()
    {
        String numar = size + ": ";

        for(int i = 0 ; i < size ; i++)
        {
            System.out.println(array[i]);
            numar = numar + array[i] + " ";
        }

        return numar;
    }


    public static void main(String[] args)
    {
        Queue q = new Queue();
        q.push(2);
        q.push(3);
        q.push(1);
        q.push(12);

        q.pop();
        System.out.println(q.pop());
        q.push(999);

        System.out.println(q.toString());

        q.pop();
        q.pop();
        q.pop();

        System.out.println(q.isEmpty());
    }

}