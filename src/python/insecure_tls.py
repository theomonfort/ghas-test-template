"""CWE-295 / CWE-319: TLS verification disabled."""

import ssl
import requests
import urllib3

urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)


def fetch_insecure(url: str) -> str:
    return requests.get(url, verify=False).text


def custom_context() -> ssl.SSLContext:
    ctx = ssl.create_default_context()
    ctx.check_hostname = False
    ctx.verify_mode = ssl.CERT_NONE
    return ctx
