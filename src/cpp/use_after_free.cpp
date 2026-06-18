// CWE-416 / CWE-415: Use-after-free and double free.
#include <cstring>
#include <cstdlib>

void process() {
    char* data = static_cast<char*>(std::malloc(64));
    if (!data) return;
    std::strcpy(data, "payload");
    std::free(data);
    std::strcpy(data, "again");   // use after free
    std::free(data);              // double free
}
