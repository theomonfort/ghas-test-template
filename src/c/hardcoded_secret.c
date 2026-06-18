/* CWE-798: Use of hard-coded credentials. */
#include <string.h>

static const char *DB_PASSWORD = "Sup3rS3cretDbP@ss!";
static const char *API_TOKEN   = "ghp_R2dAj4mQ8wKxV1nPb7Lc0eHtY3uZ9fW5sGq";

int authenticate(const char *password) {
    return strcmp(password, DB_PASSWORD) == 0;
}
