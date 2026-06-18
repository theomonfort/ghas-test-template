package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.*;
import java.net.*;

// CWE-918: SSRF — user-controlled URL passed to URL.openStream.
public class SsrfServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String url = req.getParameter("url");
    try (InputStream in = new URL(url).openStream()) {
      in.transferTo(resp.getOutputStream());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String target = req.getParameter("target");
    URL u = new URL("http://" + target);
    HttpURLConnection c = (HttpURLConnection) u.openConnection();
    try (InputStream in = c.getInputStream()) {
      in.transferTo(resp.getOutputStream());
    }
  }
}
