"""Tests for coverage_demo.calculator.

These intentionally cover only PART of the module so the GitHub Code Coverage
report on pull requests shows partial coverage:
  - multiply() and fizzbuzz() are never called  -> uncovered functions
  - classify()'s "negative" branch is not tested -> uncovered branch
  - safe_divide()'s b == 0 branch is not tested   -> uncovered branch
"""

from coverage_demo.calculator import add, subtract, classify, safe_divide


def test_add():
    assert add(2, 3) == 5


def test_subtract():
    assert subtract(7, 4) == 3


def test_classify():
    assert classify(5) == "positive"
    assert classify(0) == "zero"


def test_safe_divide():
    assert safe_divide(9, 3) == 3
