package telran.producer.consumer;

import java.util.Arrays;

public class Main {
    private static final int N_RECEIVERS = 10;
    private static final int N_MESSAGES = 2000;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBoxEven = new SimpleMessageBox();
        MessageBox messageBoxOdd = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, messageBoxEven, messageBoxOdd, "Sender");
        Receiver[] receivers = new Receiver[N_RECEIVERS];
        for (int i = 0; i < N_RECEIVERS; i++) {
            MessageBox currentMessageBox = (i + 1) % 2 == 0 ? messageBoxEven : messageBoxOdd;
            receivers[i] = new Receiver(currentMessageBox, "Receiver #%d".formatted(i + 1));
            receivers[i].start();
        }
        sender.start();
        sender.join();
        Arrays.stream(receivers).forEach(Receiver::interrupt);
    }
}