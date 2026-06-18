// CWE-352: Missing CSRF protection.
// Rule: js/missing-token-validation
// Autofix: typically NOT provided — fix requires installing & wiring up
// a token middleware (csurf, csrf-csrf, or a SameSite-cookie strategy)
// which is an architectural decision.

const express = require('express');
const session = require('express-session');

const app = express();
app.use(express.urlencoded({ extended: true }));
app.use(session({ secret: 'keyboard cat', resave: false, saveUninitialized: false }));

app.post('/transfer', (req, res) => {
  const { to, amount } = req.body;
  res.json({ transferred: amount, to });
});

app.post('/email/change', (req, res) => {
  req.session.user = req.session.user || { id: 1 };
  req.session.user.email = req.body.email;
  res.json({ ok: true });
});

app.post('/delete-account', (req, res) => {
  res.json({ deleted: true });
});

app.listen(3110);
