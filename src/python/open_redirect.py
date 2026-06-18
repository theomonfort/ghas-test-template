"""CWE-601: Open redirect."""

from flask import Flask, request, redirect

app = Flask(__name__)


@app.route("/login")
def login():
    next_url = request.args.get("next", "/")
    return redirect(next_url)


@app.route("/out")
def out():
    return redirect(request.args.get("url"))


if __name__ == "__main__":
    app.run(port=4008)
