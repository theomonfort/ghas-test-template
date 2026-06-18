package com.octodemo.vuln;

import java.util.Random;
import java.util.UUID;

// CWE-330 / CWE-338: Insecure randomness for security tokens.
// Rule: java/insecure-randomness, java/random-used-only-once
// Autofix: typically NOT provided — fix requires switching to SecureRandom
// and possibly a key-derivation function.
public final class InsecureRandomness {

  private static final Random RNG = new Random();

  public static String sessionId() {
    return Long.toHexString(RNG.nextLong());
  }

  public static String resetToken() {
    Random r = new Random(System.currentTimeMillis());
    return Integer.toHexString(r.nextInt());
  }

  public static String apiKey() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 16; i++) {
      sb.append(Integer.toHexString(RNG.nextInt(16)));
    }
    return sb.toString();
  }

  public static String predictableUuid() {
    return new UUID(RNG.nextLong(), RNG.nextLong()).toString();
  }

  private InsecureRandomness() {}
}
