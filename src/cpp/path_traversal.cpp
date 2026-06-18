// CWE-22: Path traversal — untrusted filename from the environment.
#include <cstdlib>
#include <fstream>
#include <string>

int main() {
    const char* filename = std::getenv("USER_FILE");   // e.g. ../../etc/passwd
    if (!filename) return 1;
    std::ifstream in("/srv/data/" + std::string(filename));   // path injection sink
    std::string content((std::istreambuf_iterator<char>(in)),
                        std::istreambuf_iterator<char>());
    return content.empty() ? 1 : 0;
}
