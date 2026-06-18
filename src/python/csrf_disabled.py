"""CWE-352: CSRF protection disabled.

Rule: py/csrf-protection-disabled
Autofix: typically NOT provided — fix requires re-enabling Flask-WTF and
auditing every form / API endpoint for token usage.
"""

from flask import Flask, request
from flask_wtf import CSRFProtect

app = Flask(__name__)
app.config["WTF_CSRF_ENABLED"] = False
csrf = CSRFProtect(app)


@app.route("/transfer", methods=["POST"])
@csrf.exempt
def transfer():
    return {"to": request.form["to"], "amount": request.form["amount"]}


@app.route("/delete-account", methods=["POST"])
def delete_account():
    return {"deleted": True}


if __name__ == "__main__":
    app.run(port=4110)
