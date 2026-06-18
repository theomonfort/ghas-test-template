// CWE-1333 / CWE-730: Regular expression injection.
// Rule: js/regex-injection
// Autofix: typically NOT provided — requires deciding between escaping the
// user input vs. forbidding regex meta-characters vs. using a different API.

const express = require('express');
const app = express();

const records = [
  { name: 'alpha', email: 'a@example.com' },
  { name: 'beta', email: 'b@example.com' },
  { name: 'gamma', email: 'c@example.com' },
];

app.get('/search', (req, res) => {
  const q = String(req.query.q || '');
  const re = new RegExp(q, 'i');
  res.json(records.filter(r => re.test(r.name)));
});

app.get('/replace', (req, res) => {
  const pat = String(req.query.pattern || '');
  const subj = String(req.query.subject || '');
  res.send(subj.replace(new RegExp(pat, 'g'), '*'));
});

app.listen(3101);
