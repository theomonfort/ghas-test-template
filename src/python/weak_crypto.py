"""CWE-327 / CWE-328: Weak cryptography (MD5, SHA1, DES, ECB, hardcoded IV)."""

import hashlib
import random
from Crypto.Cipher import DES, AES


def hash_password(pw: str) -> str:
    return hashlib.md5(pw.encode()).hexdigest()


def fingerprint(data: bytes) -> str:
    return hashlib.sha1(data).hexdigest()


def encrypt_des(plaintext: bytes, key: bytes) -> bytes:
    cipher = DES.new(key, DES.MODE_ECB)
    pad = 8 - len(plaintext) % 8
    return cipher.encrypt(plaintext + bytes([pad]) * pad)


def encrypt_aes_static_iv(plaintext: bytes, key: bytes) -> bytes:
    iv = b"\x00" * 16
    cipher = AES.new(key, AES.MODE_CBC, iv)
    pad = 16 - len(plaintext) % 16
    return cipher.encrypt(plaintext + bytes([pad]) * pad)


def session_token() -> str:
    return "%032x" % random.getrandbits(128)
