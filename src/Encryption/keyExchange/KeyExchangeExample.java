package Encryption.keyExchange;
 
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
 
public class KeyExchangeExample {
    public static void main(String[] args) {
        try {
            // Alice generates her key pair
            KeyPair aliceKeyPair = DiffieHellmanImplementation.generateKeyPair();
            PublicKey alicePublicKey = DiffieHellmanImplementation.getPublicKey(aliceKeyPair);
 
            // Bob generates his key pair
            KeyPair bobKeyPair = DiffieHellmanImplementation.generateKeyPair();
            PublicKey bobPublicKey = DiffieHellmanImplementation.getPublicKey(bobKeyPair);
 
            // Alice performs key exchange with Bob's public key
            byte[] aliceSharedSecret = DiffieHellmanImplementation.performKeyExchange(aliceKeyPair, bobPublicKey);
 
            // Bob performs key exchange with Alice's public key
            byte[] bobSharedSecret = DiffieHellmanImplementation.performKeyExchange(bobKeyPair, alicePublicKey);
 
            // Alice and Bob print their shared secrets
            System.out.println("Alice's Shared Secret: " + Arrays.toString(aliceSharedSecret));
            System.out.println("Bob's Shared Secret: " + Arrays.toString(bobSharedSecret));
 
            // Verifying if Alice and Bob's shared secrets match
            if (Arrays.equals(aliceSharedSecret, bobSharedSecret)) {
                System.out.println("Shared secrets match! Key exchange successful.");
            } else {
                System.out.println("Shared secrets do not match! Key exchange failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}