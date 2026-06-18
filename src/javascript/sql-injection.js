// CWE-89: SQL Injection
// User-controlled input is concatenated directly into a SQL statement.

const express = require('express');
const mysql = require('mysql');

const app = express();
const db = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'hunter2',
  database: 'app',
});

app.get('/user', (req, res) => {
  const userId = req.query.id;
  const sql = "SELECT * FROM users WHERE id = '" + userId + "'";
  db.query(sql, (err, rows) => {
    if (err) return res.status(500).send(err.message);
    res.json(rows);
  });
});

app.get('/search', (req, res) => {
  const term = req.query.q;
  db.query(`SELECT * FROM products WHERE name LIKE '%${term}%'`, (err, rows) => {
    if (err) return res.status(500).send(err.message);
    res.json(rows);
  });
});

app.post('/login', express.urlencoded({ extended: true }), (req, res) => {
  const { username, password } = req.body;
  const sql = `SELECT * FROM users WHERE username = '${username}' AND password = '${password}'`;
  db.query(sql, (err, rows) => {
    if (err) return res.status(500).send(err.message);
    res.json({ ok: rows.length > 0 });
  });
});

app.listen(3000);
