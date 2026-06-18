// CWE-94: Code injection via eval / Function constructor.

const express = require('express');
const app = express();

app.get('/calc', (req, res) => {
  const expr = req.query.expr;
  const result = eval(expr);
  res.send(String(result));
});

app.get('/run', (req, res) => {
  const code = req.query.code;
  const fn = new Function(code);
  res.send(String(fn()));
});

app.get('/template', (req, res) => {
  const tpl = req.query.t;
  const rendered = new Function('return `' + tpl + '`')();
  res.send(rendered);
});

app.listen(3007);
