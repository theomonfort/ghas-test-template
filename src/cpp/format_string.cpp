// CWE-134: Uncontrolled format string sourced from the environment.
#include <cstdio>
#include <cstdlib>

void logInput() {
    const char* userInput = std::getenv("USER_MSG");
    if (userInput) std::printf(userInput);   // attacker controls the format string
}
