/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.cryptographie_RSA_AES_project.exo2;

/**
 *
 * @author Mohamed
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class RSA_AES_Gen {

    private SecretKeySpec secretKey;

    public RSA_AES_Gen(int length, String algorithm) 
        throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {

        SecureRandom rnd = new SecureRandom();
        byte [] key = new byte [length];
        rnd.nextBytes(key);
        this.secretKey = new SecretKeySpec(key, algorithm);

    }

    public SecretKeySpec getKey(){
        return this.secretKey;
    }

    public void writeToFile(String path, byte[] key) throws IOException {

        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();

    }

    public static void main(String[] args) 
        throws NoSuchAlgorithmException, NoSuchPaddingException, IOException {

        RSA_AES_Gen genSK = new RSA_AES_Gen(16, "AES");
        genSK.writeToFile("Clé_unique/cléSecrete", genSK.getKey().getEncoded());
        System.out.println("Clé symétrique générèe avec succes !");
         BufferedReader lireFichier = null;
         String ligne;
         try {
            lireFichier = new BufferedReader(new FileReader("Clé_unique/cléSecrete"));
            
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }
        System.out.println("La clé symétrique (AES) générèe  est : ");
        while ((ligne = lireFichier.readLine()) != null) {
            System.out.println(ligne);
        }
        lireFichier.close();

    }
}