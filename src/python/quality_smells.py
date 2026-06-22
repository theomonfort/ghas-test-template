"""Code-quality smells (NOT security) detected by CodeQL `security-and-quality`.

These trigger maintainability/reliability alerts such as unused imports,
unused variables, identical comparisons, duplicate dict keys, and unreachable code.
"""

import os
import sys  # noqa: F401 - unused import (py/unused-import)


def comparison_using_is(value):
    # py/comparison-using-is: 'is' compared against a literal
    if value is "active":
        return True
    return False


def identical_comparison(x):
    # py/comparison-of-identical-expressions: always true
    if x == x:
        return 1
    return 0


def duplicate_dict_key():
    # py/duplicate-key-dict-literal
    config = {"timeout": 30, "retries": 3, "timeout": 60}
    return config


def unused_local_and_unreachable(items):
    total = 0
    unused = 42  # py/unused-local-variable
    for item in items:
        total += item
    return total
    print("this never runs")  # py/unreachable-statement


def redundant_comparison(flag):
    # py/redundant-comparison: comparing a boolean to True
    if flag == True:
        return "yes"
    return "no"
