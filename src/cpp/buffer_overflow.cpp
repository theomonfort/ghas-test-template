// CWE-120 / CWE-787: Buffer overflow — environment input copied into a fixed stack buffer.
#include <cstdio>
#include <cstdlib>
#include <cstring>

void copyName() {
    const char* name = std::getenv("USER_NAME");   // untrusted source
    char buf[16];
    std::strcpy(buf, name ? name : "");            // no bounds check
    std::printf("hello %s\n", buf);
}
