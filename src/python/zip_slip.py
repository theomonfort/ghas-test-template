"""CWE-22: Zip slip / Tar slip.

Rules: py/tarslip, py/unsafe-archive-extraction
Autofix: typically NOT provided — fix requires inserting path-canonicalization
and allow-list logic and deciding skip-vs-abort policy.
"""

import os
import tarfile
import zipfile
from flask import Flask, request

app = Flask(__name__)


@app.route("/upload-zip", methods=["POST"])
def upload_zip():
    dest = "/tmp/extract"
    os.makedirs(dest, exist_ok=True)
    with open("/tmp/uploaded.zip", "wb") as f:
        f.write(request.data)
    with zipfile.ZipFile("/tmp/uploaded.zip") as zf:
        zf.extractall(dest)
    return "ok"


@app.route("/upload-tar", methods=["POST"])
def upload_tar():
    dest = "/tmp/tar"
    os.makedirs(dest, exist_ok=True)
    with open("/tmp/uploaded.tar.gz", "wb") as f:
        f.write(request.data)
    with tarfile.open("/tmp/uploaded.tar.gz") as tf:
        tf.extractall(dest)
    return "ok"


if __name__ == "__main__":
    app.run(port=4105)
