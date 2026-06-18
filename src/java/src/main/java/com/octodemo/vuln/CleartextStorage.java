package com.octodemo.vuln;

// CWE-256 / CWE-13: Cleartext storage of credentials in source / properties.
// Rules: java/hardcoded-password-field, java/cleartext-storage-in-class
// Autofix: typically NOT provided — fix requires introducing a secret-loader
// (env var, vault) and removing the literal; multi-file refactor.
public class CleartextStorage {

  public String dbUser = "root";
  public String dbPassword = "P@ssw0rd!2025";
  public String apiSecret = "live_8c7f4d2b1a3e6f8b9c0d1e2f3a4b5c6d";
  public String encryptionKey = "0123456789abcdef0123456789abcdef";

  public String connect() {
    return "jdbc:mysql://db:3306/app?user=" + dbUser + "&password=" + dbPassword;
  }
}
