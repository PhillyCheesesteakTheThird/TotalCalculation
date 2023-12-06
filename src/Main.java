import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
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
                for (int i = 0; i < 23; i++) {
                    Random rd = new Random();
                    int intValue = rd.nextInt(0, 100);
                    float floatValue = rd.nextFloat();
                    float value = intValue + floatValue;
                    String valueString = String.format("%.2f", value);
                    fileWriter.write(valueString + "\n");
                }
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Something broke!");
        }
    }

    public static void readFile(String fileName) {
        ArrayList<Float> valueList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00");
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
            fileWriter.write("Your sub-total is: $" + df.format(subTotal) + "\n");
            fileWriter.write("Your tax is: $" + df.format(tax) + " (5.3%) \n");
            fileWriter.write("Your total is: $" + df.format(total) + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something broke!");
        }

    }
}