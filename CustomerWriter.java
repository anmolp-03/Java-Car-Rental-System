package database;

//<----------------------class for the writing in the file------------->//
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomerWriter {
    public CustomerWriter(String customerName, String customerID) throws IOException{
        FileWriter fileWriter = new FileWriter("customers.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String customerDetails = customerName + "," + customerID + "," ;
        bufferedWriter.write(customerDetails + "\n");
        bufferedWriter.close();
    }
    public static void writeCustomerToFile(String customerName, String customerID) throws IOException {
        FileWriter fileWriter = new FileWriter("customers.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String customerDetails = customerName + "," + customerID + "," ;
        bufferedWriter.write(customerDetails + "\n");
        bufferedWriter.close();
    }
}

//public class db {
//}