/* CWE-338: Cryptographically weak PRNG used to generate a security token. */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(void) {
    char token[17];
    srand((unsigned)time(NULL));         /* predictable seed */
    for (int i = 0; i < 16; i++)
        token[i] = "0123456789abcdef"[rand() % 16];
    token[16] = '\0';
    printf("session=%s\n", token);
    return 0;
}
