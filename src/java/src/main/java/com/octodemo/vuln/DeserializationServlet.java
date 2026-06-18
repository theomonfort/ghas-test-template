package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.*;
import java.util.Base64;

// CWE-502: Insecure Java deserialization of untrusted data.
public class DeserializationServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String cookie = req.getHeader("X-State");
    byte[] raw = Base64.getDecoder().decode(cookie);
    try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(raw))) {
      Object obj = in.readObject();
      resp.getWriter().println("restored=" + obj);
    } catch (ClassNotFoundException e) {
      resp.sendError(400, e.getMessage());
    }
  }
}
