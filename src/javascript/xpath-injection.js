// CWE-643: XPath injection.
// Rule: js/xpath-injection
// Autofix: typically NOT provided — XPath has no parameterized API in
// xpath/xmldom; fix requires either an escaping function or a redesign
// using XPath variables (xpath.parse + evaluate with context).

const express = require('express');
const xpath = require('xpath');
const { DOMParser } = require('xmldom');

const app = express();
const doc = new DOMParser().parseFromString(`
  <users>
    <user role="admin"><name>alice</name><email>a@example.com</email></user>
    <user role="user"><name>bob</name><email>b@example.com</email></user>
  </users>
`);

app.get('/user', (req, res) => {
  const name = String(req.query.name || '');
  const expr = `//user[name="${name}"]/email/text()`;
  const nodes = xpath.select(expr, doc);
  res.json(nodes.map(n => n.toString()));
});

app.get('/by-role', (req, res) => {
  const role = String(req.query.role || '');
  const nodes = xpath.select(`//user[@role='${role}']/name/text()`, doc);
  res.json(nodes.map(n => n.toString()));
});

app.listen(3106);
