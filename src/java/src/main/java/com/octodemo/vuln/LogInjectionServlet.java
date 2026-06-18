package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.*;

// CWE-117: Log injection (CRLF).
// Rule: java/log-injection (string concat form)
// Autofix: typically NOT provided — sanitization vs. structured-logging
// switch is application-design.
public class LogInjectionServlet extends HttpServlet {

  private static final PrintStream LOG = System.out;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String user = req.getParameter("user");
    LOG.println("login attempt by " + user);
    String action = req.getParameter("action");
    LOG.printf("[audit] action=%s%n", action);
    resp.getWriter().println("ok");
  }
}
