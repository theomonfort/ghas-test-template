// CWE-416: Use-after-free of a heap object.
#include <cstring>
#include <cstdlib>

int main() {
    char* data = static_cast<char*>(std::malloc(64));
    if (!data) return 1;
    std::strcpy(data, "payload");
    std::free(data);
    std::strcpy(data, "again");   // use after free
    std::free(data);              // double free
    return 0;
}
