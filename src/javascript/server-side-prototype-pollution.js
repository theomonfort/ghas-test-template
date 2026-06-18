// CWE-1321: Server-side prototype pollution.
// Rule: js/prototype-polluting-assignment, js/prototype-pollution
// Autofix: typically NOT provided — fix requires choosing a safer merge
// strategy (e.g. structuredClone + per-property allowlist, switching to
// lodash.mergeWith with a guard, or rejecting __proto__/constructor keys).

const express = require('express');
const app = express();
app.use(express.json());

function setDeep(target, pathStr, value) {
  const keys = pathStr.split('.');
  let cur = target;
  for (let i = 0; i < keys.length - 1; i++) {
    if (cur[keys[i]] == null) cur[keys[i]] = {};
    cur = cur[keys[i]];
  }
  cur[keys[keys.length - 1]] = value;
}

app.post('/update', (req, res) => {
  const cfg = {};
  for (const k of Object.keys(req.body)) {
    setDeep(cfg, k, req.body[k]);
  }
  res.json(cfg);
});

app.post('/merge-into-globals', (req, res) => {
  const target = {};
  Object.assign(target, req.body);
  for (const k in target) {
    if (typeof target[k] === 'object' && target[k] !== null) {
      Object.assign(Object.prototype, target[k]);
    }
  }
  res.json({ ok: true });
});

app.listen(3103);
