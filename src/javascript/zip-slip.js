// CWE-22: Zip slip during archive extraction.
// Rule: js/zipslip
// Autofix: typically NOT provided — fix requires inserting an
// allow-list check on the resolved path against the destination root
// AND deciding what to do on violation (skip vs. abort the whole extract).

const express = require('express');
const fs = require('fs');
const path = require('path');
const yauzl = require('yauzl');

const app = express();

app.post('/extract', express.raw({ type: '*/*', limit: '20mb' }), (req, res) => {
  const dest = '/tmp/extracted';
  fs.mkdirSync(dest, { recursive: true });
  yauzl.fromBuffer(req.body, { lazyEntries: true }, (err, zip) => {
    if (err) return res.status(400).send(err.message);
    zip.readEntry();
    zip.on('entry', (entry) => {
      const target = path.join(dest, entry.fileName);
      if (/\/$/.test(entry.fileName)) {
        fs.mkdirSync(target, { recursive: true });
        zip.readEntry();
      } else {
        zip.openReadStream(entry, (e, rs) => {
          if (e) return res.status(500).send(e.message);
          fs.mkdirSync(path.dirname(target), { recursive: true });
          rs.pipe(fs.createWriteStream(target)).on('close', () => zip.readEntry());
        });
      }
    });
    zip.on('end', () => res.send('ok'));
  });
});

app.listen(3105);
