// CWE-78: OS command injection — environment input concatenated into system().
#include <cstdlib>
#include <string>

int main() {
    const char* host = std::getenv("PING_HOST");   // attacker-controlled source
    std::string cmd = "ping -c 1 " + std::string(host ? host : "localhost");
    std::system(cmd.c_str());                       // command injection sink
    return 0;
}
