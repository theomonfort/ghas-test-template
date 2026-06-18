// CWE-918: Server-Side Request Forgery (SSRF).

const express = require('express');
const axios = require('axios');

const app = express();

app.get('/fetch', async (req, res) => {
  const url = req.query.url;
  const r = await axios.get(url);
  res.type('text/plain').send(r.data);
});

app.get('/preview', async (req, res) => {
  const target = req.query.target;
  const html = (await axios.get('http://' + target)).data;
  res.type('text/html').send(html);
});

app.get('/avatar', async (req, res) => {
  const user = req.query.user;
  const r = await axios.get(`http://internal-avatars.local/${user}.png`, { responseType: 'arraybuffer' });
  res.type('image/png').send(Buffer.from(r.data));
});

app.listen(3004);
