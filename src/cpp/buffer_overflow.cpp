// CWE-120 / CWE-787: Buffer overflow — argv copied into a fixed stack buffer.
#include <cstdio>
#include <cstring>

int main(int argc, char** argv) {
    char buf[16];
    if (argc > 1) {
        std::strcpy(buf, argv[1]);     // no bounds check on untrusted input
        std::printf("hello %s\n", buf);
    }
    return 0;
}
