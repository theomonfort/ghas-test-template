// CWE-120 / CWE-787: Buffer overflow via strcpy into a fixed stack buffer.
#include <cstdio>
#include <cstring>

void copyName(const char* name) {
    char buf[16];
    std::strcpy(buf, name);          // no bounds check
    std::printf("hello %s\n", buf);
}
