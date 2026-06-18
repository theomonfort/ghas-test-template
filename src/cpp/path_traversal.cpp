// CWE-22: Path traversal — untrusted filename appended to a base directory.
#include <fstream>
#include <string>

std::string readUserFile(const std::string& filename) {
    std::ifstream in("/srv/data/" + filename);   // ../../etc/passwd
    std::string content((std::istreambuf_iterator<char>(in)),
                        std::istreambuf_iterator<char>());
    return content;
}
