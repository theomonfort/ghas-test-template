"""CWE-918: SSRF via requests on user-controlled URLs."""

import requests
from flask import Flask, request

app = Flask(__name__)


@app.route("/fetch")
def fetch():
    url = request.args.get("url", "")
    r = requests.get(url, timeout=5)
    return r.text


@app.route("/preview")
def preview():
    target = request.args.get("target", "")
    return requests.get("http://" + target).text


@app.route("/webhook", methods=["POST"])
def webhook():
    callback = request.json.get("callback")
    requests.post(callback, json={"event": "ping"})
    return "ok"


if __name__ == "__main__":
    app.run(port=4004)
