package com.octodemo.vuln;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

// CWE-295: Insecure TrustManager / HostnameVerifier accept all.
// Rules: java/insecure-trustmanager, java/insecure-hostname-verifier
// Autofix: typically NOT provided — fix requires correctly configuring a
// CA bundle and pinning policy.
public final class InsecureTls {

  public static SSLContext insecureContext() throws Exception {
    TrustManager[] trustAll = new TrustManager[] {
        new X509TrustManager() {
          public void checkClientTrusted(X509Certificate[] c, String a) {}
          public void checkServerTrusted(X509Certificate[] c, String a) {}
          public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
        }
    };
    SSLContext ctx = SSLContext.getInstance("TLS");
    ctx.init(null, trustAll, new java.security.SecureRandom());
    HttpsURLConnection.setDefaultHostnameVerifier((host, session) -> true);
    return ctx;
  }

  private InsecureTls() {}
}
