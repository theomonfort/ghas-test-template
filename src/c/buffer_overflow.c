/* CWE-120 / CWE-787: Classic buffer overflow.
 * gets() and strcpy() with no bounds checking on attacker-controlled input. */
#include <stdio.h>
#include <string.h>

void greet(const char *name) {
    char buf[16];
    strcpy(buf, name);          /* overflow if strlen(name) >= 16 */
    printf("hello %s\n", buf);
}

void read_line(void) {
    char line[32];
    gets(line);                 /* unbounded read */
    printf("you typed: %s\n", line);
}
