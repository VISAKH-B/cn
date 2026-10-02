import java.net.*;
import java.io.*;

public class slidsender {
    public static void main(String[] a) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        Socket s = ss.accept();
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream out = new PrintStream(s.getOutputStream());

        String[] buf = new String[8];
        int ptr = 0, win = 8, n, ack;
        String more;

        do {
            System.out.print("Enter no. of frames: ");
            n = Integer.parseInt(kb.readLine());
            out.println(n);

            if (n > win - 1) {
                System.out.println("Frames exceed window size");
                break;
            }
            for (int i = 1; i <= n; i++) {
                buf[ptr] = kb.readLine();
                out.println(buf[ptr]);
                ptr = ++ptr % 8;
            }
            win -= n;
            ack = Integer.parseInt(in.readLine());
            System.out.println("Acknowledgment received for " + ack + " frames");
            win += n;

            System.out.print("Send more frames? ");
            more = kb.readLine();
            out.println(more);
        } while (more.equalsIgnoreCase("yes"));

        s.close();
        ss.close();
    }
}