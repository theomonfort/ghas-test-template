// Code-quality smells (NOT security) detected by CodeQL `security-and-quality`.
// Triggers maintainability alerts: unused locals, identical comparisons,
// unused static functions, and self-assignment.

#include <string>
#include <vector>

namespace {
// cpp/unused-static-function: never called
int unusedHelper() {
    return 1;
}
}  // namespace

int identicalComparison(int x) {
    // cpp/comparison-of-identical-expressions: always true
    if (x == x) {
        return 1;
    }
    return 0;
}

int unusedLocal(const std::vector<int>& values) {
    int unused = 42;  // cpp/unused-local-variable
    int total = 0;
    for (int v : values) {
        total += v;
    }
    return total;
}

std::string selfAssignment(std::string input) {
    // cpp/self-assignment
    input = input;
    return input;
}
