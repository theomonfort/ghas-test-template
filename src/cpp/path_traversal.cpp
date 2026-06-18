// CWE-22: Path traversal — untrusted filename from the environment.
#include <cstdlib>
#include <fstream>
#include <string>

std::string readUserFile() {
    const char* filename = std::getenv("USER_FILE");   // e.g. ../../etc/passwd
    if (!filename) return "";
    std::ifstream in("/srv/data/" + std::string(filename));   // path injection sink
    return std::string((std::istreambuf_iterator<char>(in)),
                       std::istreambuf_iterator<char>());
}
