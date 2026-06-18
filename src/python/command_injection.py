"""CWE-78: OS Command Injection via os.system / subprocess shell=True."""

import os
import subprocess
from flask import Flask, request

app = Flask(__name__)


@app.route("/ping")
def ping():
    host = request.args.get("host", "")
    return os.popen("ping -c 1 " + host).read()


@app.route("/dns")
def dns():
    domain = request.args.get("domain", "")
    return subprocess.check_output(f"dig {domain}", shell=True).decode()


@app.route("/archive")
def archive():
    path = request.args.get("path", "")
    os.system(f"tar -czf /tmp/out.tgz {path}")
    return "ok"


if __name__ == "__main__":
    app.run(port=4002)
