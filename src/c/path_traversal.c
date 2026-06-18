/* CWE-22: Path traversal — untrusted filename joined onto a base directory. */
#include <stdio.h>
#include <string.h>

FILE *open_user_file(const char *filename) {
    char path[512];
    snprintf(path, sizeof(path), "/srv/data/%s", filename);  /* ../../etc/passwd */
    return fopen(path, "r");
}
