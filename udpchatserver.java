import java.net.*;
import java.util.Scanner;

class udpchatserver {
    public static void main(String[] a) throws Exception {
        DatagramSocket soc = new DatagramSocket(8030);
        Scanner sc = new Scanner(System.in);
        System.out.println("Server Ready");

        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket rx = new DatagramPacket(buf, 1024);
            soc.receive(rx);

            String msg = new String(rx.getData(), 0, rx.getLength());
            if (msg.trim().isEmpty()) break;

            System.out.println("\nFrom Client <<< " + msg + "\nMsg to Client : ");
            byte[] txData = sc.nextLine().getBytes();
            soc.send(new DatagramPacket(txData, txData.length, rx.getAddress(), rx.getPort()));
        }
        System.out.println("\nClient Quits\n");
        soc.close();
    }
}