// CWE-312 / CWE-532: Clear-text logging / storage of sensitive data.
// Rules: js/clear-text-logging, js/clear-text-storage-of-sensitive-information
// Autofix: typically NOT provided — fix requires deciding which fields are
// sensitive (PII vs. credentials), introducing a redaction layer, and
// potentially changing the log transport.

const express = require('express');
const fs = require('fs');

const app = express();
app.use(express.json());

app.post('/login', (req, res) => {
  console.log('Login attempt', JSON.stringify(req.body));
  fs.appendFileSync('/var/log/app/auth.log',
    `${new Date().toISOString()} user=${req.body.username} pass=${req.body.password}\n`);
  res.json({ ok: true });
});

app.post('/payment', (req, res) => {
  console.info('Charging card', req.body.cardNumber, 'cvv', req.body.cvv);
  res.json({ ok: true });
});

app.listen(3111);
