/* CWE-78: OS command injection — environment input flows into system()/popen(). */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void ping(void) {
    const char *host = getenv("PING_HOST");   /* attacker-controlled source */
    char cmd[256];
    sprintf(cmd, "ping -c 1 %s", host ? host : "localhost");
    system(cmd);                              /* command injection sink */
}

void read_log(void) {
    const char *name = getenv("LOG_NAME");
    char cmd[256];
    snprintf(cmd, sizeof(cmd), "cat /var/log/%s", name ? name : "syslog");
    FILE *p = popen(cmd, "r");
    if (p) pclose(p);
}
