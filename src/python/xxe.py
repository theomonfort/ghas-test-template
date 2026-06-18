"""CWE-611: XML External Entity (XXE) processing."""

import xml.etree.ElementTree as ET
from lxml import etree
from flask import Flask, request

app = Flask(__name__)


@app.route("/parse", methods=["POST"])
def parse():
    tree = ET.fromstring(request.data)
    return ET.tostring(tree)


@app.route("/parse-lxml", methods=["POST"])
def parse_lxml():
    parser = etree.XMLParser(resolve_entities=True, no_network=False)
    root = etree.fromstring(request.data, parser=parser)
    return etree.tostring(root)


if __name__ == "__main__":
    app.run(port=4006)
