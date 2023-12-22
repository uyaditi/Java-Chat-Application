package Encryption.symmetric;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;
 
public class AESEncryption implements SymmetricEncryption {
    private static final String ALGORITHM = "AES";
 
    @Override
    public String encrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
 
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
 
    @Override
    public String decrypt(String encryptedData, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
 
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
 
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