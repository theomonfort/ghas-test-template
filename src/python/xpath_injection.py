"""CWE-643: XPath injection.

Rule: py/xpath-injection
Autofix: typically NOT provided — fix requires switching to `lxml.etree.XPath`
with variables, or escaping; needs API redesign.
"""

from lxml import etree
from flask import Flask, request

app = Flask(__name__)

XML = etree.fromstring(b"""
<users>
  <user role="admin"><name>alice</name><email>a@example.com</email></user>
  <user role="user"><name>bob</name><email>b@example.com</email></user>
</users>
""")


@app.route("/email")
def email():
    name = request.args.get("name", "")
    nodes = XML.xpath(f"//user[name='{name}']/email/text()")
    return {"emails": list(nodes)}


@app.route("/by-role")
def by_role():
    role = request.args.get("role", "")
    nodes = XML.xpath('//user[@role="' + role + '"]/name/text()')
    return {"names": list(nodes)}


if __name__ == "__main__":
    app.run(port=4106)
