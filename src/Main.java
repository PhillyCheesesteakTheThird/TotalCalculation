import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        String fileName = "items.txt";
        createFile(fileName, true);
        readFile(fileName);


    }

    public static void createFile(String fileName, boolean write) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File '" + fileName + "' has been created.");
            } else {
                System.out.println("File already exists");
            }
            if (write) {
                FileWriter fileWriter = new FileWriter(fileName);
                for (int i = 0; i < 45; i++) {
                    Random rd = new Random();
                    float value = rd.nextFloat();
                    fileWriter.write(value + "\n");
                }
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Something broke!");
        }
    }

    public static void readFile(String fileName) {
        ArrayList<Float> valueList = new ArrayList<>();
        double total;
        double subTotal = 0;
        double tax;
        try {
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextFloat()) {
                float value = fileReader.nextFloat();
                valueList.add(value);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        }
        for (Float value : valueList) {
            subTotal = subTotal + value;
        }
        tax = subTotal * .053;
        total = subTotal + tax;
        try {
            createFile("total.txt", false);
            File outputFile = new File("total.txt");
            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.write("Your sub-total is: " + subTotal + "\n");
            fileWriter.write("Your tax is: " + tax + "\n");
            fileWriter.write("Your total is: " + total + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something broke!");
        }

    }
}