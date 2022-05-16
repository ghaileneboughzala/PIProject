/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author ghail
 */
public class CryptWithMD5 {
   private static MessageDigest md;

   public static String cryptWithMD5(String pass){
    try {
        md = MessageDigest.getInstance("MD5");
        byte[] passBytes = pass.getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            if (i == 1){
                sb.append("0");
            }
            if (i == 13){
                sb.append("0");
            }
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(CryptWithMD5.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;


   }
   
   private static final String AES_TRANSFORMATION_STRING = "AES";
 private static SecretKeySpec getKey(String secretKey) {
    MessageDigest digest = null;
    try {
        digest = MessageDigest.getInstance("MD5");
 
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
 
    try {
        return new SecretKeySpec(digest.digest(new String(secretKey.getBytes(),"UTF8").getBytes()), AES_TRANSFORMATION_STRING);
    }
    catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        return null;
    }
}
    
    public static String encryptAES(String toEncrypt) {
        String encrypted = null;
        try {
           // Instantiate the cipher
           Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION_STRING);
           cipher.init(Cipher.ENCRYPT_MODE, getKey("aaa"));
           // Récupère la clé secrète
            byte[] cipherText = cipher.doFinal(toEncrypt.getBytes("ISO-8859-1"));
            encrypted = new String(cipherText);
        }
        catch (Exception e) {
            System.out.println("Impossible to encrypt with AES algorithm: string=(" + toEncrypt + ")");
        }
        return encrypted;
    }
    public static String decryptAES(String toDecrypt, String secretKey) {
    String decrypted = null;
    try {
        // Instantiate the cipher
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION_STRING);
        cipher.init(Cipher.DECRYPT_MODE, getKey(secretKey));
        byte[] original = cipher.doFinal(toDecrypt.getBytes("ISO-8859-15"));
        return new String(original);
    }
    catch (Exception e) {
        e.printStackTrace();
        System.out.println("Impossible to decrypt with AES algorithm: string=(" + toDecrypt + ") message=(" + e.getMessage() + ")");
    }
    return decrypted;
}
    public static String MD5(String s) throws Exception {
                             
         MessageDigest m=MessageDigest.getInstance("MD5");
         m.update(s.getBytes(),0,s.length());
         return new BigInteger(1,m.digest()).toString(16);
         
      }
               
      public static String Decrypt(String md5_hash) throws Exception {
                             
           String api_key = "YOUR_VIP_KEY";
           URL md5online = new URL("https://www.md5online.org/api.php?d=1&p="+api_key+"&h="+md5_hash
);
           BufferedReader in = new BufferedReader(new InputStreamReader(md5online.openStream()));
                             
           String result = "";
           String inputLine;
           while ((inputLine = in.readLine()) != null)
              result = result+inputLine;
           in.close();
                             
           return result;
     }
    
}
