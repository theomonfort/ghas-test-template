package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.IOException;

// CWE-601: Open redirect.
public class OpenRedirectServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String next = req.getParameter("next");
    resp.sendRedirect(next);
  }
}
