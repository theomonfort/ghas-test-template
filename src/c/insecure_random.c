/* CWE-338: Cryptographically weak PRNG used to generate a security token. */
#include <stdlib.h>
#include <time.h>

void make_session_token(char *out, int len) {
    srand((unsigned)time(NULL));         /* predictable seed */
    for (int i = 0; i < len; i++)
        out[i] = "0123456789abcdef"[rand() % 16];
}
