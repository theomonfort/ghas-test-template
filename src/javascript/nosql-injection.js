// CWE-943: NoSQL injection on Mongoose.

const express = require('express');
const mongoose = require('mongoose');

const User = mongoose.model('User', new mongoose.Schema({
  username: String,
  password: String,
  role: String,
}));

const app = express();
app.use(express.json());

app.post('/login', async (req, res) => {
  const user = await User.findOne({
    username: req.body.username,
    password: req.body.password,
  });
  res.json({ ok: !!user });
});

app.get('/users', async (req, res) => {
  const users = await User.find({ role: req.query.role });
  res.json(users);
});

app.listen(3009);
