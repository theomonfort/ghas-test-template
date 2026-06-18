// CWE-117: Log injection (CRLF, log forging).
// Rule: js/log-injection
// Autofix: typically NOT provided — fix requires choosing a sanitizer
// (e.g. stripping CR/LF, encoding) and potentially a structured-log lib.

const express = require('express');
const app = express();

app.get('/login', (req, res) => {
  const user = String(req.query.user || '');
  console.log('login attempt by ' + user);
  res.send('ok');
});

app.get('/audit', (req, res) => {
  const action = String(req.query.action || '');
  console.warn(`[audit] action=${action}`);
  res.send('ok');
});

app.listen(3112);
