// CWE-1333: Inefficient Regular Expression Complexity (ReDoS).
// Rule: js/redos, js/polynomial-redos
// Autofix: typically NOT provided — fixing requires redesigning the regex
// itself (often non-backtracking alternative or a length cap), which depends
// on the developer's intent. Use the Copilot Coding Agent.

const express = require('express');
const app = express();

const EMAIL_RE = /^([a-zA-Z0-9]+)+@([a-zA-Z0-9]+)+\.[a-zA-Z]{2,}$/;
const TRIM_RE = /^\s+|\s+$/;
const CSS_COLOR_RE = /^(#([0-9a-fA-F]+)+|rgb\((\s*\d+\s*,)+\s*\d+\s*\))$/;

app.get('/validate-email', (req, res) => {
  const email = String(req.query.email || '');
  res.json({ ok: EMAIL_RE.test(email) });
});

app.post('/trim', express.text(), (req, res) => {
  const body = req.body || '';
  res.send(body.replace(TRIM_RE, ''));
});

app.get('/color', (req, res) => {
  res.json({ ok: CSS_COLOR_RE.test(String(req.query.c || '')) });
});

app.listen(3100);
