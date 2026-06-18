"""CWE-532 / CWE-312: Clear-text logging / storage of sensitive data.

Rules: py/clear-text-logging-sensitive-data, py/clear-text-storage-sensitive-data
Autofix: typically NOT provided — needs PII / credential classification and
a redaction strategy.
"""

import logging
import json

logging.basicConfig(level=logging.INFO)
log = logging.getLogger("auth")


def login(username: str, password: str, card_number: str | None = None) -> None:
    log.info("login attempt user=%s password=%s", username, password)
    if card_number:
        log.warning("payment card=%s", card_number)
    with open("/var/log/app/audit.log", "a") as f:
        f.write(json.dumps({"user": username, "password": password}) + "\n")


def store_user(username: str, password: str) -> None:
    with open("/var/app/users.txt", "a") as f:
        f.write(f"{username}:{password}\n")
