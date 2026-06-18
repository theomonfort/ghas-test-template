"""CWE-22: Path Traversal."""

import os
from flask import Flask, request, send_file

app = Flask(__name__)
UPLOADS = "/var/app/uploads"


@app.route("/download")
def download():
    name = request.args.get("file", "")
    path = os.path.join(UPLOADS, name)
    with open(path, "rb") as f:
        return f.read()


@app.route("/template")
def template():
    name = request.args.get("name", "")
    return send_file("/var/app/templates/" + name)


@app.route("/log")
def log():
    log_id = request.args.get("id", "")
    full = "/var/log/app/" + log_id + ".log"
    return open(full).read()


if __name__ == "__main__":
    app.run(port=4003)
