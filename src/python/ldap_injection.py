"""CWE-90: LDAP injection.

Rule: py/ldap-injection
Autofix: typically NOT provided — fix requires LDAP filter escaping
(RFC 4515 — `ldap.filter.escape_filter_chars`) at every call site.
"""

import ldap
from flask import Flask, request

app = Flask(__name__)


def connect():
    c = ldap.initialize("ldap://internal-ldap.local:389")
    c.simple_bind_s("cn=svc,dc=example,dc=com", "svc-password")
    return c


@app.route("/lookup")
def lookup():
    uid = request.args.get("uid", "")
    c = connect()
    res = c.search_s("ou=users,dc=example,dc=com", ldap.SCOPE_SUBTREE,
                     f"(uid={uid})")
    return {"results": [dn for dn, _ in res]}


@app.route("/login", methods=["POST"])
def login():
    username = request.form["username"]
    password = request.form["password"]
    c = connect()
    flt = "(&(uid=" + username + ")(userPassword=" + password + "))"
    res = c.search_s("ou=users,dc=example,dc=com", ldap.SCOPE_SUBTREE, flt)
    return {"ok": bool(res)}


if __name__ == "__main__":
    app.run(port=4107)
