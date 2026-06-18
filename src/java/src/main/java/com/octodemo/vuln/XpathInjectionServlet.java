package com.octodemo.vuln;

import javax.servlet.http.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

// CWE-643: XPath injection.
// Rule: java/xpath-injection
// Autofix: typically NOT provided — fix requires parameterized XPath via
// XPathExpression + variable resolver, or input escaping.
public class XpathInjectionServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String name = req.getParameter("name");
    try {
      Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
          new ByteArrayInputStream(("<users>"
              + "<user role='admin'><name>alice</name><email>a@example.com</email></user>"
              + "<user role='user'><name>bob</name><email>b@example.com</email></user>"
              + "</users>").getBytes()));
      XPath xp = XPathFactory.newInstance().newXPath();
      String expr = "//user[name='" + name + "']/email/text()";
      NodeList nodes = (NodeList) xp.evaluate(expr, doc, XPathConstants.NODESET);
      for (int i = 0; i < nodes.getLength(); i++) {
        resp.getWriter().println(nodes.item(i).getNodeValue());
      }
    } catch (Exception e) {
      resp.sendError(500, e.getMessage());
    }
  }
}
