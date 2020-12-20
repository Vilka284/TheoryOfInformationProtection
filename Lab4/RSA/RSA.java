package RSA;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class RSA {

    public static KeyPair keyPair;
    public static PublicKey publickey;
    public static PrivateKey privateKey;

    public RSA() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert keyPairGenerator != null;
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.genKeyPair();
        publickey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }

    public void encrypt(String filePath) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publickey);
        FileInputStream fis = new FileInputStream(filePath);
        FileOutputStream fos = new FileOutputStream("crypt_text.txt");
        makeCipher(cipher, fis, fos);
    }

    public void decrypyt(String filePath) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        FileInputStream fis = new FileInputStream(filePath);
        FileOutputStream fos = new FileOutputStream("decrypt_text.txt");
        makeCipher(cipher, fis, fos);
    }

    private void makeCipher(Cipher cipher, FileInputStream fis, FileOutputStream fos) throws IOException {
        CipherOutputStream cos = new CipherOutputStream(fos, cipher);
        byte[] bytes = new byte[30];
        int i = 0;
        while ((i = fis.read(bytes)) != -1) {
            cos.write(bytes, 0, i);
        }
        cos.close();
    }
}
