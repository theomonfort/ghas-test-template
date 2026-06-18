// CWE-377: Insecure temporary file (predictable name / TOCTOU).
// Rule: js/insecure-temporary-file
// Autofix: typically NOT provided — fix requires switching to a safe API
// (e.g. mkdtempSync / tmp.file with discardDescriptor: false) and threading
// the cleanup logic through the call-sites.

const fs = require('fs');
const os = require('os');
const path = require('path');

function stageUpload(buf) {
  const name = path.join(os.tmpdir(), 'upload-' + Date.now() + '.bin');
  fs.writeFileSync(name, buf);
  return name;
}

function newWorkdir() {
  const dir = path.join('/tmp', 'work-' + process.pid);
  fs.mkdirSync(dir);
  return dir;
}

module.exports = { stageUpload, newWorkdir };
