// CWE-601: Open redirect.

const express = require('express');
const app = express();

app.get('/login', (req, res) => {
  const next = req.query.next || '/';
  res.redirect(next);
});

app.get('/out', (req, res) => {
  res.writeHead(302, { Location: req.query.url });
  res.end();
});

app.listen(3008);
