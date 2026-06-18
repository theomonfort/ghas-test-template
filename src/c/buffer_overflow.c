/* CWE-120 / CWE-787: Buffer overflow.
 * Untrusted input (argv / stdin) copied into a fixed stack buffer with no bounds check. */
#include <stdio.h>
#include <string.h>

int main(int argc, char **argv) {
    char buf[16];
    if (argc > 1) {
        strcpy(buf, argv[1]);        /* overflow if argv[1] is longer than 15 chars */
        printf("hello %s\n", buf);
    }
    char line[32];
    gets(line);                      /* unbounded read from stdin */
    printf("you typed: %s\n", line);
    return 0;
}
