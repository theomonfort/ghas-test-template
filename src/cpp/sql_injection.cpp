// CWE-89: SQL injection — environment input concatenated into a query string.
#include <cstdlib>
#include <string>

extern int db_exec(const char* sql);   // pretend DB API

int main() {
    const char* userId = std::getenv("USER_ID");   // untrusted source
    std::string sql = "SELECT * FROM users WHERE id = '" +
                      std::string(userId ? userId : "") + "'";
    return db_exec(sql.c_str());                    // SQL sink
}
