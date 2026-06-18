"""CWE-502: Unsafe pickle and yaml.load deserialization."""

import pickle
import base64
import yaml
from flask import Flask, request

app = Flask(__name__)


@app.route("/session", methods=["POST"])
def session():
    cookie = request.headers.get("X-Session", "")
    obj = pickle.loads(base64.b64decode(cookie))
    return str(obj)


@app.route("/restore", methods=["POST"])
def restore():
    raw = request.data
    state = pickle.loads(raw)
    return repr(state)


@app.route("/config", methods=["POST"])
def config():
    return repr(yaml.load(request.data))


if __name__ == "__main__":
    app.run(port=4005)
