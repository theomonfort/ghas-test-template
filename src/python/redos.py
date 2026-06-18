"""CWE-1333 / CWE-730: ReDoS via catastrophic backtracking.

Rule: py/redos, py/polynomial-redos
Autofix: typically NOT provided — fix requires redesigning the regex
(remove nested quantifiers, anchor properly, switch to a parser, or
impose a length cap). Use the Copilot Coding Agent.
"""

import re
from flask import Flask, request

app = Flask(__name__)

EMAIL_RE = re.compile(r"^([a-zA-Z0-9]+)+@([a-zA-Z0-9]+)+\.[a-zA-Z]{2,}$")
CSS_COLOR_RE = re.compile(r"^(#([0-9a-fA-F]+)+|rgb\((\s*\d+\s*,)+\s*\d+\s*\))$")
TRIM_RE = re.compile(r"^\s+|\s+$")


@app.route("/email")
def email():
    e = request.args.get("e", "")
    return {"ok": bool(EMAIL_RE.match(e))}


@app.route("/color")
def color():
    c = request.args.get("c", "")
    return {"ok": bool(CSS_COLOR_RE.match(c))}


@app.route("/trim", methods=["POST"])
def trim():
    return TRIM_RE.sub("", request.data.decode())


if __name__ == "__main__":
    app.run(port=4100)
