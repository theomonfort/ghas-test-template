/* CWE-416 / CWE-415: Use-after-free and double free. */
#include <stdlib.h>
#include <string.h>

void process(void) {
    char *data = (char *)malloc(64);
    if (!data) return;
    strcpy(data, "payload");
    free(data);
    strcpy(data, "again");   /* use after free */
    free(data);              /* double free */
}
