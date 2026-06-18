package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

// CWE-22: Path traversal.
public class PathTraversalServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String name = req.getParameter("file");
    Path p = Paths.get("/var/app/uploads", name);
    byte[] data = Files.readAllBytes(p);
    resp.getOutputStream().write(data);
  }

  protected void serveLog(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String id = req.getParameter("id");
    try (FileInputStream in = new FileInputStream("/var/log/app/" + id + ".log")) {
      in.transferTo(resp.getOutputStream());
    }
  }
}
