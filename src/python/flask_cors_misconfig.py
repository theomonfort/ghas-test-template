"""CWE-942: Permissive CORS configuration.

Rule: py/flask-cors-permissive-configuration
Autofix: typically NOT provided — fix requires choosing an origin allow-list
which depends on the deployment topology.
"""

from flask import Flask, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app, supports_credentials=True, origins="*")


@app.route("/me")
def me():
    return jsonify(user="alice", email="alice@example.com", secret="🤐")


if __name__ == "__main__":
    app.run(port=4104)
