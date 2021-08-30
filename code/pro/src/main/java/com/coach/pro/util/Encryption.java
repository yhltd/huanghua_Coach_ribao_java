package com.coach.pro.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author wanghui
 * @date 2021/08/30 10:17
 */
public class Encryption {
    public static final String KEY = "FnJL7EDzjqWjcbps";//密钥key
    public static final String IV  = "FnJL7EDzjqWjcaY9";//偏移量iv

    //aes-cbc-128加密 返回hex
    public static String Encrypt(String content) throws Exception {
    byte[] raw = KEY.getBytes("utf-8");
    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
    //使用CBC模式，需要一个向量iv，可增加加密算法的强度
    IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ips);
    byte[] encrypted = cipher.doFinal(content.getBytes());
    System.out.println("加密后的值:"+Hex.encodeHexString(encrypted));
    return Hex.encodeHexString(encrypted);
    //return new Base64().encodeToString(encrypted);
    }
}
