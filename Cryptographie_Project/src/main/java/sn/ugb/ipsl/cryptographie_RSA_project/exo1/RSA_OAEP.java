/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.cryptographie_RSA_project.exo1;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
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
public class RSA_OAEP {
    private static Scanner sc;
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        sc = new Scanner(System.in);
        System.out.println("Donner un caractere ou mot en entrée : ");
        
        byte[] input = sc.nextLine().getBytes();
        byte[] secretMessage = input;
        Cipher cipher = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");
        SecureRandom random = new SecureRandom();
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");

        generator.initialize(1024, random);

        KeyPair pair = generator.generateKeyPair();
        Key pubKey = pair.getPublic();

        Key privKey = pair.getPrivate();

        cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);
        byte[] cipherText = cipher.doFinal(input);
        System.out.println("Chiffrement avec cipher: " + new String(cipherText));

        byte[] secretMessageBytes = secretMessage;
        byte[] encryptedMessageBytes = cipher.doFinal(secretMessageBytes);
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        System.out.println("Encodage dans la base 64 : " + encodedMessage);
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("Texte en clair : " + new String(plainText));
    }
}
