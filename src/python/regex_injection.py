"""CWE-730: Regular expression injection.

Rule: py/regex-injection
Autofix: typically NOT provided — fix requires `re.escape` on user input
or rejecting regex metacharacters; the choice depends on intent.
"""

import re
from flask import Flask, request

app = Flask(__name__)


@app.route("/search")
def search():
    q = request.args.get("q", "")
    pat = re.compile(q)
    candidates = ["alpha", "beta", "gamma", "delta"]
    return {"matches": [c for c in candidates if pat.search(c)]}


@app.route("/replace")
def replace():
    pat = request.args.get("p", "")
    subj = request.args.get("s", "")
    return re.sub(pat, "*", subj)


if __name__ == "__main__":
    app.run(port=4101)
