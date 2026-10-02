#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

int main() {
    int sock;
    struct sockaddr_in addr;
    char msg[1024];

    sock = socket(AF_INET, SOCK_STREAM, 0);

    addr.sin_family = AF_INET;
    addr.sin_port = htons(8080);
    addr.sin_addr.s_addr = inet_addr("127.0.0.1");

    connect(sock, (struct sockaddr*)&addr, sizeof(addr));

    char sendmsg[] = "Hello Server";
    send(sock, sendmsg, strlen(sendmsg), 0);

    int n = read(sock, msg, sizeof(msg) - 1);
    msg[n] = '\0';
    printf("Server: %s\n", msg);

    close(sock);
    return 0;
}