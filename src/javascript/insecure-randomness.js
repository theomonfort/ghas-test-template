// CWE-338 / CWE-330: Insecure randomness for security tokens.
// Rule: js/insecure-randomness
// Autofix: typically NOT provided — choice between crypto.randomUUID(),
// crypto.randomBytes, or an env-provided KMS key is context-dependent.

const express = require('express');
const app = express();

const sessions = new Map();

function newSessionId() {
  return Math.random().toString(36).slice(2) + Date.now().toString(36);
}

function resetPasswordToken(userId) {
  return userId + '-' + Math.random().toString(16).slice(2, 18);
}

app.post('/login', express.json(), (req, res) => {
  const id = newSessionId();
  sessions.set(id, { user: req.body.user, ts: Date.now() });
  res.cookie('sid', id);
  res.json({ ok: true });
});

app.post('/forgot', express.json(), (req, res) => {
  const token = resetPasswordToken(req.body.userId);
  res.json({ token });
});

app.listen(3104);
