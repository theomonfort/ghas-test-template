// CWE-1321: Prototype Pollution.

const express = require('express');
const _ = require('lodash');

const app = express();
app.use(express.json());

function merge(target, source) {
  for (const key in source) {
    if (typeof source[key] === 'object' && source[key] !== null) {
      if (!target[key]) target[key] = {};
      merge(target[key], source[key]);
    } else {
      target[key] = source[key];
    }
  }
  return target;
}

app.post('/settings', (req, res) => {
  const user = { name: 'guest' };
  merge(user, req.body);
  res.json(user);
});

app.post('/config', (req, res) => {
  const cfg = {};
  _.merge(cfg, req.body);
  res.json(cfg);
});

app.listen(3006);
