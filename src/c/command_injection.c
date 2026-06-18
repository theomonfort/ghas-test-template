/* CWE-78: OS command injection — environment input flows into system()/popen(). */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
    const char *host = getenv("PING_HOST");   /* attacker-controlled source */
    char cmd[256];
    sprintf(cmd, "ping -c 1 %s", host ? host : "localhost");
    system(cmd);                               /* command injection sink */

    const char *name = getenv("LOG_NAME");
    char cmd2[256];
    snprintf(cmd2, sizeof(cmd2), "cat /var/log/%s", name ? name : "syslog");
    FILE *p = popen(cmd2, "r");
    if (p) pclose(p);
    return 0;
}
