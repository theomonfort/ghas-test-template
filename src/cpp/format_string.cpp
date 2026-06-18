// CWE-134: Uncontrolled format string passed to printf.
#include <cstdio>

void logInput(const char* userInput) {
    std::printf(userInput);    // attacker controls the format string
}
