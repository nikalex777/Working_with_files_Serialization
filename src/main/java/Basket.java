import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    protected String[] product;
    protected int[] price;
    protected int[] amount;
    protected int sumProducts;


    public Basket(String[] productName, int[] price) {
        this.product = productName;
        this.price = price;
        this.amount = new int[productName.length];
    }

    public void addToCart(int productNum, int amounts) {
        amount[productNum] += amounts;
    }

    public void printCart() {

        System.out.println("Ваша корзина:");

        for (int i = 0; i < product.length; i++) {
            if (amount[i] != 0) {
                System.out.println(product[i] + " " + amount[i] + " шт., " + price[i] + " руб., " + (amount[i] * price[i]) + " рублей в сумме.");
                sumProducts += amount[i] * price[i];
            }
        }
        System.out.println("Итого: " + sumProducts + " рублей.");
    }

    public void saveToJSON(File textFile) throws IOException {
        FileWriter writer = new FileWriter(textFile);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        writer.write(gson.toJson(this, Basket.class));
        writer.close();
    }

    public static Basket loadFromJSON(File textFile) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(textFile);
        return gson.fromJson(reader, Basket.class);
    }
}