/* CWE-190: Integer overflow leading to an undersized allocation. */
#include <stdlib.h>
#include <string.h>

void *dup_items(const void *src, unsigned int count, unsigned int size) {
    unsigned int total = count * size;   /* may wrap around */
    void *dst = malloc(total);
    if (dst) memcpy(dst, src, count * size);
    return dst;
}
