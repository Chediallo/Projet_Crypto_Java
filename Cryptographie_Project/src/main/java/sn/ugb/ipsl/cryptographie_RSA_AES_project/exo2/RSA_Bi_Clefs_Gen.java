/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.cryptographie_RSA_AES_project.exo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author Mohamed
 */
public class RSA_Bi_Clefs_Gen {
    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSA_Bi_Clefs_Gen(int keylength) 
        throws NoSuchAlgorithmException, NoSuchProviderException {

        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength);

    }

    public void createKeys() {

        this.pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();

    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public void writeToFile(String path, byte[] key) throws IOException {

        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, IOException {

        RSA_Bi_Clefs_Gen gk_Alice;
        RSA_Bi_Clefs_Gen gk_Bob;

        gk_Alice = new RSA_Bi_Clefs_Gen(1024);
        gk_Alice.createKeys();
        gk_Alice.writeToFile("Bi_Clefs/publicKey_Mama", gk_Alice.getPublicKey().getEncoded());
        gk_Alice.writeToFile("Bi_Clefs/privateKey_Mama", gk_Alice.getPrivateKey().getEncoded());

        gk_Bob = new RSA_Bi_Clefs_Gen(1024);
        gk_Bob.createKeys();
        gk_Bob.writeToFile("Bi_Clefs/publicKey_Moha", gk_Bob.getPublicKey().getEncoded());
        gk_Bob.writeToFile("Bi_Clefs/privateKey_Moha", gk_Bob.getPrivateKey().getEncoded());
        System.out.println("Les clés publiques et privées de Mama et de Moha sont générées avec success !");
        BufferedReader lireFichier1 = null,lireFichier2 = null,lireFichier3 = null,lireFichier4 = null;
        String ligne1,ligne2,ligne3,ligne4;
        
        try {
            lireFichier1 = new BufferedReader(new FileReader("Bi_Clefs/publicKey_Mama"));
            
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }
        System.out.println("La clé publique de Mama est : ");
        while ((ligne1 = lireFichier1.readLine()) != null) {
            System.out.println(ligne1);
        }
        lireFichier1.close();
        System.out.println("\n");
         try {
            lireFichier2 = new BufferedReader(new FileReader("Bi_Clefs/privateKey_Mama"));
            
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }
        System.out.println("La clé privée de Mama est : ");
        while ((ligne2 = lireFichier2.readLine()) != null) {
            System.out.println(ligne2);
        }
        lireFichier2.close();
        System.out.println("\n");
        try {
            lireFichier3 = new BufferedReader(new FileReader("Bi_Clefs/publicKey_Moha"));
            
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }
        System.out.println("La clé publique de Moha est : ");
        while ((ligne3 = lireFichier3.readLine()) != null) {
            System.out.println(ligne3);
        }
        lireFichier3.close();
        System.out.println("\n");
        try {
            lireFichier4 = new BufferedReader(new FileReader("Bi_Clefs/privateKey_Moha"));
            
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }
        System.out.println("La clé privée de Moha est : ");
        while ((ligne4 = lireFichier4.readLine()) != null) {
            System.out.println(ligne4);
        }
        lireFichier4.close();

    }

}
