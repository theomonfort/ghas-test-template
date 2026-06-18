package com.octodemo.vuln;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.*;
import java.io.IOException;

// CVE-2021-44228: Log4Shell — vulnerable log4j-core 2.14.1 + user-controlled string logged.
public class Log4ShellServlet extends HttpServlet {
  private static final Logger log = LogManager.getLogger(Log4ShellServlet.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String ua = req.getHeader("User-Agent");
    log.info("Visit from {}", ua);
    String name = req.getParameter("name");
    log.warn("hello " + name);
    resp.getWriter().println("ok");
  }
}
