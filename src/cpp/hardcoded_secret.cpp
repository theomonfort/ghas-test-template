// CWE-798: Hard-coded credentials embedded in source.
#include <string>
#include <cstdio>

static const std::string kAdminPassword = "P@ssw0rd-Admin-2026!";

bool checkAdmin(const std::string& pw) {
    return pw == kAdminPassword;     // compares against hard-coded secret
}

int main(int argc, char** argv) {
    if (argc > 1) std::printf("admin=%d\n", checkAdmin(argv[1]));
    return 0;
}
