package com.algorithm;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream; 
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

 
public class Encryptor 
{
	
 public void encrypt(String fname) throws Exception
 {
  KeyGenerator keyGen = KeyGenerator.getInstance("AES");
  keyGen.init(128);  
  SecretKey key = keyGen.generateKey();  //generating key
  
  Cipher cipher = Cipher.getInstance("AES");  //getting cipher for 
  
  cipher.init(Cipher.ENCRYPT_MODE, key);  //initializing cipher for encryption with key
   
  //creating file output stream to write to file
  try(FileOutputStream fos = new FileOutputStream(fname+".aes")){
   //creating object output stream to write objects to file
   ObjectOutputStream oos = new ObjectOutputStream(fos);
   oos.writeObject(key);  //saving key to file for use during decryption
 
   //creating file input stream to read contents for encryption
   try(FileInputStream fis = new FileInputStream(fname)){
    //creating cipher output stream to write encrypted contents
    try(CipherOutputStream cos = new CipherOutputStream(fos, cipher)){
     int read;
     byte buf[] = new byte[4096];
     while((read = fis.read(buf)) != -1)  //reading from file
      cos.write(buf, 0, read);  //encrypting and writing to file
    }
   }
  }
   
 }
  
}