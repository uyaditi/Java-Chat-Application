package Encryption.symmetric;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.Scanner;
 
public class DESEncryption implements SymmetricEncryption {
    private static final String ALGORITHM = "DES";
 
    @Override
    public String encrypt(String data, String key) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
 
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
 
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
 
    @Override
    public String decrypt(String encryptedData, String key) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
 
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
 
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.print("Enter the message to encrypt: ");
        String originalData = scanner.nextLine();
 
        System.out.print("Enter the encryption key for DES (8 bytes): ");
        String encryptionKey = scanner.nextLine();
 
        try {
            DESEncryption desEncryption = new DESEncryption();
            String encryptedDES = desEncryption.encrypt(originalData, encryptionKey);
            String decryptedDES = desEncryption.decrypt(encryptedDES, encryptionKey);
 
            System.out.println("Original: " + originalData);
            System.out.println("Encrypted with DES: " + encryptedDES);
            System.out.println("Decrypted with DES: " + decryptedDES);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}