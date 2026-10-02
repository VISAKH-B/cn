import java.net.*;
import java.util.Scanner;

class udpchatclient {
    public static void main(String[] a) throws Exception {
        DatagramSocket soc = new DatagramSocket();
        InetAddress ip = InetAddress.getByName(a.length > 0 ? a[0] : "127.0.0.1");
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Enter without text to quit");

        while (true) {
            System.out.print("\nEnter text for server : ");
            String msg = sc.nextLine();
            byte[] txData = msg.getBytes();
            soc.send(new DatagramPacket(txData, txData.length, ip, 8030));

            if (msg.trim().isEmpty()) break;

            byte[] rxData = new byte[1024];
            DatagramPacket rx = new DatagramPacket(rxData, 1024);
            soc.receive(rx);
            System.out.println("From Server <<< " + new String(rx.getData(), 0, rx.getLength()));
        }
        soc.close();
    }
}