package backend.User.ultils;

import backend.User.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemFileUltils {

    public static ArrayList<Item> getDataFromFile(String fileName) {

        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Type type = new TypeToken<ArrayList<Item>>() {
            }.getType();
            ArrayList<Item> item = gson.fromJson(reader, type);
            reader.close();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void setDataToFile(String fileName, Object obj) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get(fileName));
            gson.toJson(obj, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printCart(ArrayList<Item> items) {
        System.out.printf("%-5s %-20s %-10s %-15s %-20s \n", "Id", "Tên sách", "Số lượng", "Giá", "Thành tiền");
        System.out.println("---------------------------------------------------------------------");

        for (Item item : items) {
            System.out.printf("%-5d %-20s %-10s %-15s %-20s \n", item.getId(), item.getBookName(), item.getCount(),
                    formattingDisplay(item.getPrice()), formattingDisplay(item.getCount() * item.getPrice()));
        }


    }

    public static String formattingDisplay(int price) {
        DecimalFormat formatter = new DecimalFormat("###,###,### VND");
        return formatter.format(price);
    }

}
//    private String email;
//    private int id;
//    private String bookName;
//    private int count;
//    private int price;
//    private int money;
//
//    public void setMoney(int money) {
//        this.money = count * price;
//    }