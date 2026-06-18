/* CWE-22: Path traversal — untrusted filename from the environment joined onto a base dir. */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE *open_user_file(void) {
    const char *filename = getenv("USER_FILE");   /* e.g. ../../etc/passwd */
    if (!filename) return NULL;
    char path[512];
    snprintf(path, sizeof(path), "/srv/data/%s", filename);
    return fopen(path, "r");                       /* path injection sink */
}
