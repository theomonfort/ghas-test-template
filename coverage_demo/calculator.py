"""Small pure-Python module used to demonstrate GitHub native Code Coverage.

The accompanying tests (tests/test_calculator.py) deliberately exercise only
some of these functions and branches, so the coverage report posted on pull
requests shows less than 100% — handy for demoing Code Quality / coverage.
"""


def add(a, b):
    return a + b


def subtract(a, b):
    return a - b


def multiply(a, b):
    # Intentionally left untested -> appears uncovered in the coverage report.
    return a * b


def classify(n):
    if n < 0:
        return "negative"  # this branch is intentionally not tested
    if n == 0:
        return "zero"
    return "positive"


def fizzbuzz(n):
    # Intentionally left untested -> appears uncovered in the coverage report.
    if n % 15 == 0:
        return "fizzbuzz"
    if n % 3 == 0:
        return "fizz"
    if n % 5 == 0:
        return "buzz"
    return str(n)


def safe_divide(a, b):
    if b == 0:
        raise ValueError("division by zero")  # this branch is intentionally not tested
    return a / b
