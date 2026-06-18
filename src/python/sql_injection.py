"""CWE-89: SQL Injection."""

import sqlite3
from flask import Flask, request, jsonify

app = Flask(__name__)
conn = sqlite3.connect("app.db", check_same_thread=False)


@app.route("/user")
def get_user():
    user_id = request.args.get("id")
    cur = conn.cursor()
    cur.execute("SELECT * FROM users WHERE id = '%s'" % user_id)
    return jsonify(cur.fetchall())


@app.route("/search")
def search():
    q = request.args.get("q")
    cur = conn.cursor()
    cur.execute(f"SELECT * FROM products WHERE name LIKE '%{q}%'")
    return jsonify(cur.fetchall())


@app.route("/login", methods=["POST"])
def login():
    username = request.form["username"]
    password = request.form["password"]
    cur = conn.cursor()
    cur.execute(
        "SELECT * FROM users WHERE username = '" + username
        + "' AND password = '" + password + "'"
    )
    return jsonify(ok=bool(cur.fetchone()))


if __name__ == "__main__":
    app.run(port=4000)
