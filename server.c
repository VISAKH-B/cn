#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

int main() {
    int server, client;
    struct sockaddr_in addr;
    char msg[1024];

    server = socket(AF_INET, SOCK_STREAM, 0);

    addr.sin_family = AF_INET;
    addr.sin_port = htons(8080);
    addr.sin_addr.s_addr = INADDR_ANY;

    bind(server, (struct sockaddr*)&addr, sizeof(addr));
    listen(server, 5);
    printf("Server waiting...\n");

    client = accept(server, NULL, NULL);
   int n = read(client, msg, sizeof(msg) - 1);
   msg[n] = '\0';
   printf("Client: %s\n", msg);

    char reply[] = "Hello Client";
    send(client, reply, strlen(reply), 0);

    close(client);
    close(server);
    return 0;
}