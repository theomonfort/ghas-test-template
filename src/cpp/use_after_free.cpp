// CWE-416: Use-after-free of a heap object.
#include <cstring>
#include <cstdlib>

void process() {
    char* data = static_cast<char*>(std::malloc(64));
    if (!data) return;
    std::strcpy(data, "payload");
    std::free(data);
    std::strcpy(data, "again");   // use after free
}
