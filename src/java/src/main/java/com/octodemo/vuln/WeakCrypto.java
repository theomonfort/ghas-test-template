package com.octodemo.vuln;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

// CWE-327 / CWE-328: Weak crypto (MD5, SHA1, DES, ECB, hardcoded IV, weak RNG).
public class WeakCrypto {

  public static String md5(String s) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] d = md.digest(s.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte b : d) sb.append(String.format("%02x", b));
    return sb.toString();
  }

  public static String sha1(String s) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-1");
    return new String(md.digest(s.getBytes()));
  }

  public static byte[] encryptDes(byte[] plaintext, byte[] key) throws Exception {
    Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
    c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DES"));
    return c.doFinal(plaintext);
  }

  public static byte[] encryptAesStaticIv(byte[] plaintext, byte[] key) throws Exception {
    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
    IvParameterSpec iv = new IvParameterSpec(new byte[16]);
    c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), iv);
    return c.doFinal(plaintext);
  }

  public static String sessionToken() {
    return Long.toHexString(new java.util.Random().nextLong());
  }
}
