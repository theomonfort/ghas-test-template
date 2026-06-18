// CWE-502: Deserialization of untrusted data.

const express = require('express');
const serialize = require('node-serialize');

const app = express();
app.use(express.json());

app.post('/session', (req, res) => {
  const cookie = req.headers['x-session'];
  if (cookie) {
    const obj = serialize.unserialize(Buffer.from(cookie, 'base64').toString());
    res.json(obj);
  } else {
    res.status(400).send('missing cookie');
  }
});

app.post('/restore', (req, res) => {
  const state = serialize.unserialize(req.body.state);
  res.json({ restored: state });
});

app.listen(3005);
