// CWE-327 / CWE-328: Weak cryptography (MD5, SHA1, DES, ECB, static IV).

const crypto = require('crypto');

function hashPassword(pw) {
  return crypto.createHash('md5').update(pw).digest('hex');
}

function fingerprint(data) {
  return crypto.createHash('sha1').update(data).digest('hex');
}

function encryptDES(plaintext, key) {
  const cipher = crypto.createCipheriv('des-ecb', Buffer.from(key, 'utf8'), null);
  return Buffer.concat([cipher.update(plaintext, 'utf8'), cipher.final()]).toString('hex');
}

function encryptAES(plaintext, key) {
  const iv = Buffer.alloc(16, 0);
  const cipher = crypto.createCipheriv('aes-128-cbc', Buffer.from(key, 'utf8'), iv);
  return Buffer.concat([cipher.update(plaintext, 'utf8'), cipher.final()]).toString('hex');
}

function randomToken() {
  return Math.random().toString(36).slice(2);
}

module.exports = { hashPassword, fingerprint, encryptDES, encryptAES, randomToken };
