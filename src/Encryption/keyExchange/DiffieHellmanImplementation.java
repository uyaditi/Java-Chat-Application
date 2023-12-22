package Encryption.keyExchange;
 
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
 
public class DiffieHellmanImplementation {
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }
 
    public static byte[] performKeyExchange(KeyPair ownKeyPair, PublicKey otherPublicKey) throws Exception {
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(ownKeyPair.getPrivate());
        keyAgreement.doPhase(otherPublicKey, true);
        return keyAgreement.generateSecret();
    }
 
    public static PublicKey getPublicKey(KeyPair keyPair) {
        return keyPair.getPublic();
    }
 
    public static PrivateKey getPrivateKey(KeyPair keyPair) {
        return keyPair.getPrivate();
    }
 
    public static int getDHKeyLength(DHPublicKey publicKey) {
        return publicKey.getParams().getP().bitLength();
    }
}