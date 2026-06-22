/* Code-quality smells (NOT security) detected by CodeQL `security-and-quality`.
 * Triggers maintainability alerts: unused static functions, unused locals,
 * identical comparisons, and self-comparison that is always true.
 */

#include <stdio.h>

/* cpp/unused-static-function: never called */
static int unused_helper(void) {
    return 1;
}

int identical_comparison(int x) {
    /* cpp/comparison-of-identical-expressions: always true */
    if (x == x) {
        return 1;
    }
    return 0;
}

int unused_local(int a, int b) {
    int unused = 42; /* cpp/unused-local-variable */
    return a + b;
}

int constant_condition(void) {
    /* cpp/constant-comparison: condition is always true */
    if (1 == 1) {
        return 100;
    }
    return 0;
}
