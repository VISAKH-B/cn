import java.io.*;
import java.net.*;

public class fileclient1 {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket(InetAddress.getLocalHost(), 1187);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String line;
        System.out.println("File content:");
        while ((line = in.readLine()) != null) System.out.println(line);
        System.out.println("File received successfully");

        in.close();
        s.close();
    }
}