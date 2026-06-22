// Code-quality smells (NOT security) detected by CodeQL `security-and-quality`.
// Triggers maintainability alerts: unused variables, identical comparisons,
// duplicate object properties, unreachable code, and useless self-assignment.

function identicalComparison(x) {
  // js/comparison-of-identical-expressions: always true
  if (x === x) {
    return true;
  }
  return false;
}

function unusedAndUnreachable(items) {
  const unused = 42; // js/unused-local-variable
  let total = 0;
  for (const item of items) {
    total += item;
  }
  return total;
  console.log("never reached"); // js/unreachable-statement
}

function duplicateProperty() {
  // js/duplicate-property
  return { timeout: 30, retries: 3, timeout: 60 };
}

function selfAssignment(config) {
  // js/useless-assignment-to-local (self assignment)
  let value = config.value;
  value = value;
  return value;
}

function looseEquality(a) {
  // js/automatic-semicolon-insertion style / use of == instead of ===
  if (a == null) {
    return "empty";
  }
  return "set";
}

module.exports = {
  identicalComparison,
  unusedAndUnreachable,
  duplicateProperty,
  selfAssignment,
  looseEquality,
};
