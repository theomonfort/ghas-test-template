// CWE-90: LDAP injection.
// Rule: js/ldap-injection
// Autofix: typically NOT provided — fix requires LDAP filter escaping
// (RFC 4515) which is library-specific; the Agent can introduce a small
// escape helper and update each call-site.

const express = require('express');
const ldap = require('ldapjs');

const app = express();
const client = ldap.createClient({ url: 'ldap://internal-ldap.local:389' });

app.get('/lookup', (req, res) => {
  const uid = String(req.query.uid || '');
  const opts = { filter: `(uid=${uid})`, scope: 'sub' };
  client.search('ou=users,dc=example,dc=com', opts, (err, result) => {
    if (err) return res.status(500).send(err.message);
    const entries = [];
    result.on('searchEntry', (e) => entries.push(e.object));
    result.on('end', () => res.json(entries));
  });
});

app.post('/login', express.json(), (req, res) => {
  const { username, password } = req.body;
  const filter = `(&(uid=${username})(userPassword=${password}))`;
  client.search('ou=users,dc=example,dc=com', { filter, scope: 'sub' }, (err, r) => {
    if (err) return res.status(500).send(err.message);
    let ok = false;
    r.on('searchEntry', () => { ok = true; });
    r.on('end', () => res.json({ ok }));
  });
});

app.listen(3107);
