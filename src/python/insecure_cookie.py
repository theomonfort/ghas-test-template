"""CWE-1004 / CWE-614: Sensitive cookie without Secure / HttpOnly.

Rule: py/insecure-cookie
Autofix: typically NOT provided — fix requires coordinating with the
deployment environment (HTTPS termination, SameSite policy, etc.).
"""

from flask import Flask, make_response, request

app = Flask(__name__)


@app.route("/login", methods=["POST"])
def login():
    resp = make_response("ok")
    resp.set_cookie("session", "abc123")
    resp.set_cookie("user_role", "admin", secure=False, httponly=False)
    return resp


@app.route("/track")
def track():
    resp = make_response("ok")
    resp.set_cookie("uid", request.args.get("u", "0"), samesite=None)
    return resp


if __name__ == "__main__":
    app.run(port=4108)
