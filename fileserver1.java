import java.io.*;
import java.net.*;

public class fileserver1 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1187);
        Socket c = ss.accept();
        System.out.println("Connection from " + c);

        PrintWriter out = new PrintWriter(c.getOutputStream(), true);
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the text file name:");
        String fname = sin.readLine();
        File f = new File(fname);

        if (f.exists()) {
            BufferedReader in = new BufferedReader(new FileReader(fname));
            String line;
            while ((line = in.readLine()) != null) out.println(line);
            System.out.println("File sent successfully");
            in.close();
        } else {
            System.out.println("File does not exist");
        }
        c.close();
        ss.close();
    }
}