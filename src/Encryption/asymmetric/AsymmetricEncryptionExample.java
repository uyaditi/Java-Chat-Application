package Encryption.asymmetric;
 
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;
 
public class AsymmetricEncryptionExample {
    public static void main(String[] args) {
        try {
            // Generate key pair
            KeyPair keyPair = RSAImplementation.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
 
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the message to encrypt: ");
            String originalMessage = scanner.nextLine();
            scanner.close();
 
            // Encrypt the user input message using the public key
            String encryptedMessage = RSAImplementation.encrypt(originalMessage, publicKey);
            System.out.println("Encrypted Message: " + encryptedMessage);
 
            // Decrypt the encrypted message using the private key
            String decryptedMessage = RSAImplementation.decrypt(encryptedMessage, privateKey);
            System.out.println("Decrypted Message: " + decryptedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}