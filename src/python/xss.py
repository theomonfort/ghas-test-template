"""CWE-79: Reflected XSS via render_template_string and unsafe Markup."""

from flask import Flask, request, render_template_string, Markup

app = Flask(__name__)


@app.route("/hello")
def hello():
    name = request.args.get("name", "world")
    return render_template_string("<h1>Hello " + name + "!</h1>")


@app.route("/bio")
def bio():
    text = request.args.get("text", "")
    return "<div>" + text + "</div>"


@app.route("/comment")
def comment():
    body = request.args.get("body", "")
    return render_template_string("<p>{{ b|safe }}</p>", b=Markup(body))


if __name__ == "__main__":
    app.run(port=4001)
