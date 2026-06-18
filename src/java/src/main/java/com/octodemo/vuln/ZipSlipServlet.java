package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.*;
import java.util.zip.*;

// CWE-22: Zip slip.
// Rule: java/zipslip
// Autofix: typically NOT provided — fix requires canonical-path validation
// + a policy decision on extracted-entry naming.
public class ZipSlipServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    File dest = new File("/tmp/zipdest");
    dest.mkdirs();
    try (ZipInputStream zis = new ZipInputStream(req.getInputStream())) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        File out = new File(dest, entry.getName());
        if (entry.isDirectory()) {
          out.mkdirs();
        } else {
          out.getParentFile().mkdirs();
          try (FileOutputStream fos = new FileOutputStream(out)) {
            zis.transferTo(fos);
          }
        }
      }
    }
    resp.getWriter().println("ok");
  }
}
