package telran.producer.consumer;

import java.util.Arrays;

public class Main {
    private static final int N_RECEIVERS = 10;
    private static final int N_MESSAGES = 20;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBox = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, messageBox, "Sender");
        Receiver[] receivers = new Receiver[N_RECEIVERS];
        for (int i = 0; i < N_RECEIVERS; i++) {
            receivers[i] = new Receiver(messageBox, "Receiver #%d".formatted(i + 1));
            receivers[i].start();
        }
        sender.start();
        sender.join();
        Arrays.stream(receivers).forEach(Receiver::interrupt);
    }
}