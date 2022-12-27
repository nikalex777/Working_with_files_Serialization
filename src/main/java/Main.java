import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;


public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        Basket basket = new Basket(products, prices);

        File jsonFile = new File("basket.json");
        File logFile = new File("log.csv");
        ClientLog clientLog = new ClientLog();

        if (jsonFile.exists()) {
            basket = Basket.loadFromJSON(jsonFile);
            basket.printCart();
        }

        System.out.println("Список возможных товаров: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб./шт.");
        }

        while (true) {

            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }

            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);
            basket.saveToJSON(jsonFile);
            basket.printCart();
            clientLog.log(productNumber, productCount);
            clientLog.exportAsCSV(logFile);

        }
    }
}
