"""CWE-117: Log injection.

Rule: py/log-injection
Autofix: typically NOT provided — fix requires a sanitizer (strip CR/LF,
encode) and possibly switching to structured logging.
"""

import logging
from flask import Flask, request

logging.basicConfig(level=logging.INFO)
log = logging.getLogger(__name__)

app = Flask(__name__)


@app.route("/login")
def login():
    user = request.args.get("user", "")
    log.info("login attempt by " + user)
    return "ok"


@app.route("/audit")
def audit():
    action = request.args.get("action", "")
    log.warning(f"[audit] action={action}")
    return "ok"


if __name__ == "__main__":
    app.run(port=4109)
