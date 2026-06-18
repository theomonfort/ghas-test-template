// CWE-190: Integer overflow in allocation size computation.
#include <cstdlib>
#include <cstring>

void* dupItems(const void* src, unsigned count, unsigned size) {
    unsigned total = count * size;        // may wrap
    void* dst = std::malloc(total);
    if (dst) std::memcpy(dst, src, count * size);
    return dst;
}
