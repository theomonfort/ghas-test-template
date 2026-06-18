package com.octodemo.vuln;

import javax.servlet.http.*;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import java.io.IOException;

// CWE-611: XML External Entity processing enabled by default.
public class XxeServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(req.getInputStream());
      resp.getWriter().println(doc.getDocumentElement().getNodeName());
    } catch (Exception e) {
      resp.sendError(400, e.getMessage());
    }
  }
}
