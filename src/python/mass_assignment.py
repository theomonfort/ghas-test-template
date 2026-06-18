"""CWE-915: Mass assignment / over-posting.

Rule: py/dict-update-with-untrusted (approximate); CodeQL detects via taint.
Autofix: typically NOT provided — fix requires an allow-list of mutable
fields per endpoint, which is application-design knowledge.
"""

from flask import Flask, request

app = Flask(__name__)

USERS = {
    1: {"id": 1, "username": "alice", "role": "user", "is_admin": False}
}


@app.route("/profile/update", methods=["POST"])
def update():
    user = USERS[1]
    user.update(request.get_json() or {})
    return user


@app.route("/profile/patch", methods=["POST"])
def patch():
    body = request.get_json() or {}
    for k, v in body.items():
        USERS[1][k] = v
    return USERS[1]


if __name__ == "__main__":
    app.run(port=4111)
