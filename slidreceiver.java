import java.net.*;
import java.io.*;

public class slidreceiver {
    public static void main(String[] a) throws Exception {
        Socket s = new Socket(InetAddress.getLocalHost(), 5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream out = new PrintStream(s.getOutputStream());

        String[] buf = new String[8];
        int ptr = -1, win = 8, n;
        String more;

        do {
            n = Integer.parseInt(in.readLine());
            if (n > win - 1) break;

            for (int i = 1; i <= n; i++) {
                ptr = ++ptr % 8;
                buf[ptr] = in.readLine();
                System.out.println("Received Frame " + ptr + ": " + buf[ptr]);
            }
            win -= n;
            System.out.println("Acknowledgment sent");
            out.println(ptr + 1);
            win += n;

            more = in.readLine();
        } while (more.equalsIgnoreCase("yes"));

        s.close();
    }
}