// CWE-78: OS command injection by concatenating untrusted input into system().
#include <cstdlib>
#include <string>

void ping(const std::string& host) {
    std::string cmd = "ping -c 1 " + host;   // host is attacker-controlled
    std::system(cmd.c_str());
}
