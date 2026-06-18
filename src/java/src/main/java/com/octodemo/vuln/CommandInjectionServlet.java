package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.*;

// CWE-78: OS command injection via Runtime.exec with a shell.
public class CommandInjectionServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String host = req.getParameter("host");
    Process p = Runtime.getRuntime().exec(new String[] {"sh", "-c", "ping -c 1 " + host});
    try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
      String line;
      while ((line = r.readLine()) != null) {
        resp.getWriter().println(line);
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String path = req.getParameter("path");
    Runtime.getRuntime().exec("tar -czf /tmp/out.tgz " + path);
    resp.getWriter().println("ok");
  }
}
