/* CWE-134: Uncontrolled format string. */
#include <stdio.h>
#include <syslog.h>

void log_user(const char *user_input) {
    printf(user_input);          /* attacker controls the format string */
    syslog(LOG_INFO, user_input);
}
