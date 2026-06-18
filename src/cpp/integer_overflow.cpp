// CWE-190: Integer overflow in allocation size computation.
#include <cstdlib>
#include <cstring>

int main(int argc, char** argv) {
    unsigned count = (argc > 1) ? static_cast<unsigned>(std::atoi(argv[1])) : 0;
    unsigned size  = 8;
    unsigned total = count * size;        // may wrap
    char* dst = static_cast<char*>(std::malloc(total));
    if (dst) std::memset(dst, 0, count * size);   // heap overflow when total wrapped
    std::free(dst);
    return 0;
}
