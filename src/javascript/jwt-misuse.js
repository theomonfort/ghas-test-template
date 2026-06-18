// CWE-347: JWT verification missing or weak.
// Rules: js/jwt-missing-verification, js/missing-token-validation
// Autofix: typically NOT provided — requires knowing which signing algorithm
// the issuer uses, where the public key/secret lives, and what to do on
// failure. Best left to the Coding Agent which can plumb config end-to-end.

const express = require('express');
const jwt = require('jsonwebtoken');

const app = express();

app.get('/me', (req, res) => {
  const token = (req.headers.authorization || '').replace(/^Bearer\s+/, '');
  const payload = jwt.decode(token);
  res.json({ user: payload && payload.sub });
});

app.get('/admin', (req, res) => {
  const token = (req.headers.authorization || '').replace(/^Bearer\s+/, '');
  jwt.verify(token, 'secret', { algorithms: ['none', 'HS256'] }, (err, decoded) => {
    if (err) return res.sendStatus(401);
    res.json({ admin: decoded.role === 'admin' });
  });
});

app.get('/internal', (req, res) => {
  const token = (req.headers.authorization || '').replace(/^Bearer\s+/, '');
  const decoded = jwt.verify(token, 'secret', { algorithms: ['none'] });
  res.json(decoded);
});

app.listen(3102);
