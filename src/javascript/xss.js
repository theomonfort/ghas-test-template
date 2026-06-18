// CWE-79: Reflected and stored Cross-Site Scripting.

const express = require('express');
const app = express();

app.get('/hello', (req, res) => {
  const name = req.query.name;
  res.setHeader('Content-Type', 'text/html');
  res.end(`<h1>Hello ${name}!</h1>`);
});

app.get('/profile', (req, res) => {
  const bio = req.query.bio;
  res.send('<div class="bio">' + bio + '</div>');
});

const comments = [];
app.post('/comment', express.urlencoded({ extended: true }), (req, res) => {
  comments.push(req.body.text);
  res.redirect('/comments');
});

app.get('/comments', (req, res) => {
  const html = comments.map(c => `<li>${c}</li>`).join('');
  res.send(`<ul>${html}</ul>`);
});

app.listen(3001);
