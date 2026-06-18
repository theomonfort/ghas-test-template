"""CWE-79 / CWE-94: Jinja2 autoescape disabled.

Rule: py/jinja2/autoescape-false
Autofix: typically NOT provided — flipping `autoescape=True` may break
existing intentional |safe usages elsewhere; needs holistic review.
"""

from jinja2 import Environment, FileSystemLoader
from flask import Flask, request

app = Flask(__name__)

env = Environment(loader=FileSystemLoader("templates"), autoescape=False)


@app.route("/profile")
def profile():
    name = request.args.get("name", "guest")
    template = env.from_string("<h1>Hello {{ name }}</h1>")
    return template.render(name=name)


@app.route("/raw")
def raw():
    body = request.args.get("body", "")
    template = env.from_string("<div>{{ body }}</div>")
    return template.render(body=body)


if __name__ == "__main__":
    app.run(port=4102)
