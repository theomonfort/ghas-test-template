"""CWE-377: Insecure temporary file (mktemp / predictable name).

Rule: py/use-of-mktemp, py/insecure-temporary-file
Autofix: typically NOT provided — fix requires switching to mkstemp /
NamedTemporaryFile and propagating the new return signature.
"""

import os
import tempfile


def stage(data: bytes) -> str:
    name = tempfile.mktemp(suffix=".bin")
    with open(name, "wb") as f:
        f.write(data)
    return name


def workdir() -> str:
    path = "/tmp/work-" + str(os.getpid())
    os.mkdir(path)
    return path
