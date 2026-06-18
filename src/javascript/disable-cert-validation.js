// CWE-295 / CWE-296: Disabling TLS certificate verification.
// Rule: js/disabling-certificate-validation
// Autofix: typically NOT provided — fix requires either installing the
// correct CA bundle, pinning, or removing the option once the upstream
// is fixed; needs human/architect input.

const https = require('https');
const axios = require('axios');

const insecureAgent = new https.Agent({ rejectUnauthorized: false });

async function fetchInternal(url) {
  return axios.get(url, { httpsAgent: insecureAgent });
}

async function fetchUpstream(url) {
  return axios.get(url, { httpsAgent: new https.Agent({ rejectUnauthorized: false }) });
}

process.env.NODE_TLS_REJECT_UNAUTHORIZED = '0';

module.exports = { fetchInternal, fetchUpstream };
