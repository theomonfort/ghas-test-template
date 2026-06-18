package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.IOException;

// CWE-113: HTTP response splitting / header injection.
// Rule: java/http-response-splitting
// Autofix: typically NOT provided — fix requires sanitizing CR/LF or
// switching to a typed cookie / redirect API.
public class HeaderInjectionServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String lang = req.getParameter("lang");
    resp.setHeader("Content-Language", lang);
    String location = req.getParameter("next");
    resp.setHeader("Location", location);
    resp.setStatus(302);
  }
}
