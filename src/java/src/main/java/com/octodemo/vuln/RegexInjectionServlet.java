package com.octodemo.vuln;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.regex.Pattern;

// CWE-730: Regex injection + ReDoS.
// Rules: java/regex-injection, java/redos, java/polynomial-redos
// Autofix: typically NOT provided — fix requires either escaping
// (Pattern.quote) or rewriting the regex to a non-backtracking form.
public class RegexInjectionServlet extends HttpServlet {

  // Catastrophic backtracking on adversarial input.
  private static final Pattern EMAIL =
      Pattern.compile("^([a-zA-Z0-9]+)+@([a-zA-Z0-9]+)+\\.[a-zA-Z]{2,}$");

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String userPattern = req.getParameter("pat");
    String subject = req.getParameter("s");
    Pattern p = Pattern.compile(userPattern);
    resp.getWriter().println("match=" + p.matcher(subject).find());
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String email = req.getParameter("email");
    resp.getWriter().println("valid=" + EMAIL.matcher(email).matches());
  }
}
