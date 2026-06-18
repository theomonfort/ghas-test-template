/* CWE-190: Integer overflow leading to an undersized allocation. */
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char **argv) {
    unsigned int count = (argc > 1) ? (unsigned)atoi(argv[1]) : 0;
    unsigned int size  = 8;
    unsigned int total = count * size;     /* may wrap around */
    char *dst = (char *)malloc(total);
    if (dst) memset(dst, 0, count * size); /* heap overflow when total wrapped */
    free(dst);
    return 0;
}
