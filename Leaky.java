import java.util.*;

public class Leaky {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of time intervals: ");
        int n = sc.nextInt();
        System.out.print("Enter bucket size: ");
        int size = sc.nextInt();
        System.out.print("Enter output rate: ");
        int rate = sc.nextInt();

        int stored = 0;

        for (int i = 1; i <= n; i++) {
            System.out.print("\nEnter packets at time " + i + ": ");
            int in = sc.nextInt();
            System.out.println("\nAt time " + i);
            System.out.println("Packets entering = " + in);

            int avail = size - stored;
            if (in <= avail) {
                stored += in;
                System.out.println("Packet loss = 0");
            } else {
                System.out.println("Packet loss = " + (in - avail));
                stored = size;
            }
            System.out.println("Packets in bucket = " + stored);

            int sent = Math.min(stored, rate);
            stored -= sent;
            System.out.println("Packets sent out = " + sent);
            System.out.println("Packets remaining = " + stored);
            System.out.println("----------------------------------");
        }
        sc.close();
    }
}