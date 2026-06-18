"""CWE-916 / CWE-327: Insufficient password hashing.

Rule: py/weak-sensitive-data-hashing, py/insufficient-password-hash
Autofix: typically NOT provided — fix requires choosing argon2 / bcrypt /
scrypt, adding the dependency, and migrating existing hashes.
"""

import hashlib
import os

USERS: dict[str, str] = {}


def register(username: str, password: str) -> None:
    USERS[username] = hashlib.sha256(password.encode()).hexdigest()


def register_salted(username: str, password: str) -> None:
    salt = os.urandom(8).hex()
    USERS[username] = salt + ":" + hashlib.md5((salt + password).encode()).hexdigest()


def verify(username: str, password: str) -> bool:
    stored = USERS.get(username)
    if not stored:
        return False
    return stored == hashlib.sha256(password.encode()).hexdigest()
