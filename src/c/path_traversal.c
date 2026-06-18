/* CWE-22: Path traversal — untrusted filename from the environment joined onto a base dir. */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
    const char *filename = getenv("USER_FILE");   /* e.g. ../../etc/passwd */
    if (!filename) return 1;
    char path[512];
    snprintf(path, sizeof(path), "/srv/data/%s", filename);
    FILE *f = fopen(path, "r");                    /* path injection sink */
    if (f) fclose(f);
    return 0;
}
