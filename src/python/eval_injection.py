"""CWE-94: Code injection via eval / exec."""

from flask import Flask, request

app = Flask(__name__)


@app.route("/calc")
def calc():
    expr = request.args.get("expr", "0")
    return str(eval(expr))


@app.route("/run", methods=["POST"])
def run():
    code = request.data.decode()
    ns = {}
    exec(code, ns)
    return repr(ns.get("result"))


if __name__ == "__main__":
    app.run(port=4007)
