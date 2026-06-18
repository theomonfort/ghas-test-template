// CWE-942 / CWE-346: CORS misconfiguration with credentials.
// Rule: js/cors-misconfiguration-for-credentials
// Autofix: typically NOT provided — fix requires choosing an origin
// allow-list, which depends on deployment context the model can't infer.

const express = require('express');
const cors = require('cors');

const app = express();

app.use(cors({
  origin: true,
  credentials: true,
}));

app.get('/me', (req, res) => {
  res.json({ user: 'alice', email: 'alice@example.com', secret: '🤐' });
});

const echo = express();
echo.use((req, res, next) => {
  res.setHeader('Access-Control-Allow-Origin', req.headers.origin || '*');
  res.setHeader('Access-Control-Allow-Credentials', 'true');
  next();
});
echo.get('/data', (req, res) => res.json({ ok: true }));

app.listen(3108);
echo.listen(3109);
