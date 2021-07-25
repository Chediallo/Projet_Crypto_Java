/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.cryptographie_RSA_project.exo1;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
/**
 *
 * @author Mohamed
 */
public class RSA_Raw_Encryption_Decryption {

    private static Scanner sc;

    // Méthode pour la vérification de la signature 
    public static String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    //Méthode pour le Hash
    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }
    
    // Méthode principale
    public static void main(String[] args) throws FileNotFoundException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, Exception {
        KeyPairGenerator Gen = KeyPairGenerator.getInstance("RSA");
        //Initialisation des bi-cléfs a 2048bits
        Gen.initialize(2048);
        //Génération des clefs
        KeyPair pair = Gen.generateKeyPair();
        //Recuperation de la clé privéé
        PrivateKey privateKey = pair.getPrivate();
        //Recupération de la cléf publique
        PublicKey publicKey = pair.getPublic();
        System.out.println("La clé publique est : " + publicKey);
        System.out.println("La clé privée est : " + privateKey);
        // START ENCRYPTION
        sc = new Scanner(System.in);
        System.out.println("Entrer le message à crypter :");
        String secretMessage = sc.nextLine();
        //
        System.out.println("Le message en clair : " + secretMessage);
        // Initialisation de CYPHER pour le chiffrement avec la clé publique que nous avons générée précédemment
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // Transformation de notre chaîne en tableau d'octets 
        byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        System.out.println("Message secret en byte : " + secretMessageBytes);
        System.out.println("Message crypté en byte : " + encryptedMessageBytes);
        // Encodage avec la base 64
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        System.out.println("Encodage dans la base 64 : " + encodedMessage);
        // END ENCRYPTION

        //START DECRYPTION
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        //
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        //Vérification de la signature
        System.out.println("Le message décrypté : " + decryptedMessage);
        String signature = sign("foobar", pair.getPrivate());
        boolean Hash = verify("foobar", signature, pair.getPublic());
        System.out.println("Vérification de la signature et Hash : " + Hash);
    }

}
