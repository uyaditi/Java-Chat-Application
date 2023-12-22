package Encryption.symmetric;
public interface SymmetricEncryption {
    String encrypt(String data, String key) throws Exception;
    String decrypt(String encryptedData, String key) throws Exception;
}