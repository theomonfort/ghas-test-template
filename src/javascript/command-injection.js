// CWE-78: OS Command Injection via child_process.

const express = require('express');
const { exec, execSync } = require('child_process');

const app = express();

app.get('/ping', (req, res) => {
  const host = req.query.host;
  exec('ping -c 1 ' + host, (err, stdout, stderr) => {
    if (err) return res.status(500).send(stderr);
    res.type('text/plain').send(stdout);
  });
});

app.get('/dns', (req, res) => {
  const domain = req.query.domain;
  const out = execSync(`dig ${domain}`).toString();
  res.type('text/plain').send(out);
});

app.get('/backup', (req, res) => {
  const path = req.query.path;
  exec(`tar -czf /tmp/backup.tgz ${path}`, (err) => {
    if (err) return res.status(500).send(err.message);
    res.send('ok');
  });
});

app.listen(3002);
