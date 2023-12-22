
import java.util.Scanner;
import Encryption.symmetric.AESEncryption;
 
public class MyProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.print("Enter the message to encrypt: ");
        String originalData = scanner.nextLine();
 
        System.out.print("Enter the encryption key for AES (16/24/32 bytes): ");
        String encryptionKey = scanner.nextLine();
 
        try {
            AESEncryption aesEncryption = new AESEncryption();
            String encryptedAES = aesEncryption.encrypt(originalData, encryptionKey);
            String decryptedAES = aesEncryption.decrypt(encryptedAES, encryptionKey);
 
            System.out.println("Original: " + originalData);
            System.out.println("Encrypted with AES: " + encryptedAES);
            System.out.println("Decrypted with AES: " + decryptedAES);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}