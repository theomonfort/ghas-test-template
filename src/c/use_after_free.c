/* CWE-416 / CWE-415: Use-after-free and double free. */
#include <stdlib.h>
#include <string.h>

int main(void) {
    char *data = (char *)malloc(64);
    if (!data) return 1;
    strcpy(data, "payload");
    free(data);
    strcpy(data, "again");   /* use after free */
    free(data);              /* double free */
    return 0;
}
