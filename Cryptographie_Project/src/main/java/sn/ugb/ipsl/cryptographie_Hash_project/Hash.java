/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.cryptographie_Hash_project;

import java.util.Scanner;
import org.springframework.security.crypto.bcrypt.BCrypt;
/**
 *
 * @author Mohamed
 */
public class Hash {
    
    private static Scanner sc;
  
    public static String Password_Hash(
        String password)
    {
        return BCrypt.hashpw(
            password, BCrypt.gensalt());
    }
  
    public static boolean Verify_Password(
        String password,
        String hashed_password)
    {
        return BCrypt.checkpw(
            password, hashed_password);
    }
  
    public static void main(
        String args[]) throws Exception
    {
  
        sc = new Scanner(System.in);
  
        System.out.println(
            "Entrer un mot ou une phrase à hasher ");
  
        String p = sc.nextLine();
  
        String passwordHash
            = Password_Hash(p);
  
        System.out.println(
            "Hashed-password: "
            + passwordHash);
  
        System.out.println(
            "Verification: "
            + Verify_Password(
                  p, passwordHash));
    }
}
