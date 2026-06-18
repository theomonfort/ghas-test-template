package com.octodemo.vuln;

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;

// CWE-326: Inadequate encryption strength (small RSA key).
// Rule: java/insufficient-key-size
// Autofix: typically NOT provided — bumping the key size also requires
// rotating existing keys and migrating ciphertexts.
public final class InsufficientKeySize {

  public static KeyPair tinyRsa() throws NoSuchAlgorithmException {
    KeyPairGenerator g = KeyPairGenerator.getInstance("RSA");
    g.initialize(512);
    return g.generateKeyPair();
  }

  public static byte[] tinyDh() throws Exception {
    KeyPairGenerator g = KeyPairGenerator.getInstance("DH");
    g.initialize(512);
    return g.generateKeyPair().getPublic().getEncoded();
  }

  private InsufficientKeySize() {}
}
