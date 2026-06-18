package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.IOException;

// CWE-1004 / CWE-614: Insecure cookie (no Secure / HttpOnly).
// Rule: java/insecure-cookie
// Autofix: typically NOT provided — requires coordination with the deployment
// (HTTPS-only? cross-site context? SameSite policy?).
public class InsecureCookieServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Cookie session = new Cookie("session", "abc123");
    Cookie role = new Cookie("role", "admin");
    Cookie remember = new Cookie("remember", req.getParameter("u"));
    resp.addCookie(session);
    resp.addCookie(role);
    resp.addCookie(remember);
    resp.getWriter().println("ok");
  }
}
