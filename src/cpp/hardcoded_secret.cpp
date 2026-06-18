// CWE-798: Hard-coded credentials embedded in source.
#include <string>

static const std::string kAwsSecret =
    "wJalrXUtnFEMI/K7MDENG/bPxRfiCYscFakeKey42";
static const std::string kAdminPassword = "P@ssw0rd-Admin-2026!";

bool checkAdmin(const std::string& pw) {
    return pw == kAdminPassword;
}
