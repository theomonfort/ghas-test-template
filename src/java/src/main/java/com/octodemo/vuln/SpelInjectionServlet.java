package com.octodemo.vuln;

import org.springframework.expression.*;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import javax.servlet.http.*;
import java.io.IOException;

// CWE-1336: Server-Side Template Injection / SpEL injection.
// Rule: java/server-side-template-injection (Thymeleaf), java/spel-injection
// Autofix: typically NOT provided — fix requires sandboxing the SpEL parser
// (SimpleEvaluationContext) or removing the dynamic expression API entirely.
public class SpelInjectionServlet extends HttpServlet {

  private static final SpelExpressionParser parser = new SpelExpressionParser();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String expr = req.getParameter("expr");
    Expression e = parser.parseExpression(expr);
    Object value = e.getValue();
    resp.getWriter().println("value=" + value);
  }
}
