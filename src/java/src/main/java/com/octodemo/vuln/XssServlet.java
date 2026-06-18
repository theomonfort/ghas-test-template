package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.IOException;

// CWE-79: Reflected XSS — user input written to the response without encoding.
public class XssServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String name = req.getParameter("name");
    resp.setContentType("text/html");
    resp.getWriter().write("<h1>Hello " + name + "!</h1>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String bio = req.getParameter("bio");
    resp.setContentType("text/html");
    resp.getWriter().write("<div class='bio'>" + bio + "</div>");
  }
}
