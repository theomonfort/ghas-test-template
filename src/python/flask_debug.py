"""CWE-489 / CWE-215: Flask running with debug=True in production code path.

Rule: py/flask-debug
Autofix: typically NOT provided — fix requires gating the value on an env
variable or removing it; the Coding Agent can wire env config end-to-end.
"""

from flask import Flask

app = Flask(__name__)


@app.route("/")
def index():
    return "hello"


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=4103, debug=True)
