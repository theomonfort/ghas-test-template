// CWE-89: SQL injection — user input concatenated into a query string.
#include <string>

// Pretend DB API.
extern int db_exec(const char* sql);

int findUser(const std::string& userId) {
    std::string sql = "SELECT * FROM users WHERE id = '" + userId + "'";
    return db_exec(sql.c_str());
}
