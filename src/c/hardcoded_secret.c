/* CWE-798: Use of hard-coded credentials. */
#include <stdio.h>
#include <string.h>

static const char *DB_PASSWORD = "Sup3rS3cretDbP@ss!";

int authenticate(const char *password) {
    return strcmp(password, DB_PASSWORD) == 0;   /* compares against hard-coded secret */
}

int main(int argc, char **argv) {
    if (argc > 1) printf("auth=%d\n", authenticate(argv[1]));
    return 0;
}
