// CWE-22: Path Traversal.

const express = require('express');
const fs = require('fs');
const path = require('path');

const app = express();

app.get('/download', (req, res) => {
  const file = req.query.file;
  const data = fs.readFileSync('/var/app/uploads/' + file);
  res.send(data);
});

app.get('/template', (req, res) => {
  const name = req.query.name;
  const tpl = fs.readFileSync(path.join('/var/app/templates', name), 'utf8');
  res.type('text/html').send(tpl);
});

app.get('/static/*', (req, res) => {
  const requested = req.params[0];
  const full = '/var/app/public/' + requested;
  res.sendFile(full);
});

app.listen(3003);
