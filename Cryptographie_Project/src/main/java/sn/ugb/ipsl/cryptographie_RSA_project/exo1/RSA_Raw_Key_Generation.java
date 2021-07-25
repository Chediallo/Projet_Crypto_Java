/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.cryptographie_RSA_project.exo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author Mohamed
 */
public class RSA_Raw_Key_Generation {
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, InvalidKeySpecException {
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
        //Enregistrement de la clé dans un fichier
        try (FileOutputStream fos = new FileOutputStream("public.key")) {
            fos.write(publicKey.getEncoded());
            // Lecture d'une clé à partir d'un fichier
            File publicKeyFile = new File("public.key");
            byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            keyFactory.generatePublic(publicKeySpec);
        }

    }
}
