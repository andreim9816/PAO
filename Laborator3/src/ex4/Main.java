package ex4;

public class Main
{
    public static void main(String[] args)
    {
        Product v[] = new Product[4];
        v[0] = new FoodProduct(27.0 , "Whooper" , "Orange" , "FastFood");
        v[1] = new ClotheProduct(199.99 , "Shirt" , "blue" , "top");
        v[2] = new CleaningProduct(13.49 , "Domestos" , "white" , "furniture");
        v[3] = new FurnitureProduct(1299.99 , "Wardrobe" , "Brown" , 210 , 80);

        for(Product p : v)
            System.out.println(p.toString());

    }
}
