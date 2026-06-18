/* CWE-120 / CWE-787: Buffer overflow.
 * Untrusted environment input copied into a fixed stack buffer with no bounds check. */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void greet(void) {
    const char *name = getenv("USER_NAME");   /* attacker-controlled source */
    char buf[16];
    strcpy(buf, name ? name : "");            /* overflow if name longer than 15 chars */
    printf("hello %s\n", buf);
}
