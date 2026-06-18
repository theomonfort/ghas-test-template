package com.octodemo.vuln;

import java.sql.*;
import javax.servlet.http.*;
import java.io.IOException;

// CWE-89: SQL injection via Statement + concatenated user input.
public class SqlInjectionServlet extends HttpServlet {

  private Connection connect() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/app", "root", "hunter2");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String userId = req.getParameter("id");
    try (Connection c = connect(); Statement s = c.createStatement()) {
      ResultSet rs = s.executeQuery("SELECT * FROM users WHERE id = '" + userId + "'");
      while (rs.next()) {
        resp.getWriter().println(rs.getString("username"));
      }
    } catch (SQLException e) {
      resp.sendError(500, e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String user = req.getParameter("username");
    String pass = req.getParameter("password");
    String sql = String.format(
        "SELECT * FROM users WHERE username = '%s' AND password = '%s'", user, pass);
    try (Connection c = connect(); Statement s = c.createStatement()) {
      ResultSet rs = s.executeQuery(sql);
      resp.getWriter().println("ok=" + rs.next());
    } catch (SQLException e) {
      resp.sendError(500, e.getMessage());
    }
  }
}
