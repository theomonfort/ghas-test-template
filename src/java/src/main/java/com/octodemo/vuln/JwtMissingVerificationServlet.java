package com.octodemo.vuln;

import io.jsonwebtoken.*;
import javax.servlet.http.*;
import java.io.IOException;

// CWE-347: JWT missing verification / "none" algorithm.
// Rule: java/jwt-missing-verification
// Autofix: typically NOT provided — fix requires a verifier with the right
// key & algorithm; key material lives in app config.
public class JwtMissingVerificationServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String auth = req.getHeader("Authorization");
    if (auth == null) { resp.sendError(401); return; }
    String token = auth.replaceFirst("^Bearer ", "");
    Jwt<?, ?> jwt = Jwts.parser().parse(token);
    Claims claims = (Claims) jwt.getBody();
    resp.getWriter().println("hello " + claims.getSubject());
  }
}
