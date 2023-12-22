package Encryption.hash;
 
import java.util.Scanner;
 
public class HashFunctionsExample {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select the hashing algorithm:");
            System.out.println("1. SHA-256");
            System.out.println("2. MD5");
            System.out.print("Enter your choice (1 or 2): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
 
            System.out.print("Enter the string to hash: ");
            String inputString = scanner.nextLine();
            scanner.close();
 
            String hashedValue;
            if (choice == 1) {
                hashedValue = SHAImplementation.hashSHA256(inputString);
                System.out.println("MD5 Hash:" + hashedValue);
            } else if (choice == 2) {
                hashedValue = MD5Implementation.hashMD5(inputString);
                System.out.println("SHA-256 Hash:" + hashedValue);
            } else {
                System.out.println("Invalid choice!");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}