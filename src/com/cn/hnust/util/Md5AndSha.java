package com.cn.hnust.util;
import java.security.MessageDigest;
/**
 * MD5数据加密操作
 * @author zcg
 * @time 2017-9-18
 *
 */
 public class Md5AndSha{ 
     public static String convertMD5(String s){ 
         char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'}; 
        try { 
             byte[] bytes = s.getBytes(); 
             MessageDigest md = MessageDigest.getInstance("md5"); 
            md.update(bytes); 
            bytes = md.digest(); 
            int j = bytes.length; 
            char[] chars = new char[j * 2]; 
           int k = 0; 
            for (int i = 0; i < bytes.length; i++) { 
                byte b = bytes[i]; 
               chars[k++] = hexChars[b >>> 4 & 0xf]; 
               chars[k++] = hexChars[b & 0xf]; 
            } 
            return new String(chars); 
        } 
        catch (Exception e){ 
            return null; 
       } 
    }
     public static String convertSHA(String s){ 
         char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'}; 
        try { 
             byte[] bytes = s.getBytes(); 
             MessageDigest md = MessageDigest.getInstance("sha"); 
            md.update(bytes); 
            bytes = md.digest(); 
            int j = bytes.length; 
            char[] chars = new char[j * 2]; 
           int k = 0; 
            for (int i = 0; i < bytes.length; i++) { 
                byte b = bytes[i]; 
               chars[k++] = hexChars[b >>> 4 & 0xf]; 
               chars[k++] = hexChars[b & 0xf]; 
            } 
            return new String(chars); 
        } 
        catch (Exception e){ 
            return null; 
       } 
    }
    
    public static void main(String[] args){
   }
} 