package com.octodemo.vuln;

import javax.naming.*;
import javax.naming.directory.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;

// CWE-74 / CWE-1336: JNDI / LDAP injection.
// Rule: java/jndi-injection, java/ldap-injection.
// Autofix: typically NOT provided — fix needs JndiManager allow-listing
// of providers, or filter-escaping for LDAP; both are architectural.
public class JndiInjectionServlet extends HttpServlet {

  private static final Set<String> ALLOWED_JNDI_NAMES = new HashSet<>();
  static {
    ALLOWED_JNDI_NAMES.add("java:comp/env/jdbc/main");
    ALLOWED_JNDI_NAMES.add("java:comp/env/jms/queue/orders");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String name = req.getParameter("name");
    if (name == null || !ALLOWED_JNDI_NAMES.contains(name)) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JNDI resource name");
      return;
    }
    try {
      InitialContext ctx = new InitialContext();
      Object obj = ctx.lookup(name);
      resp.getWriter().println(obj);
    } catch (NamingException e) {
      resp.sendError(500, e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String uid = req.getParameter("uid");
    Hashtable<String, String> env = new Hashtable<>();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://internal-ldap.local:389");
    try {
      DirContext dctx = new InitialDirContext(env);
      NamingEnumeration<SearchResult> results = dctx.search(
          "ou=users,dc=example,dc=com", "(uid=" + uid + ")", new SearchControls());
      while (results.hasMore()) {
        resp.getWriter().println(results.next().getName());
      }
    } catch (NamingException e) {
      resp.sendError(500, e.getMessage());
    }
  }
}
