/* CWE-78: OS command injection via system()/popen() with untrusted input. */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void ping(const char *host) {
    char cmd[256];
    sprintf(cmd, "ping -c 1 %s", host);   /* host is attacker-controlled */
    system(cmd);
}

void read_log(const char *name) {
    char cmd[256];
    snprintf(cmd, sizeof(cmd), "cat /var/log/%s", name);
    FILE *p = popen(cmd, "r");
    if (p) pclose(p);
}
