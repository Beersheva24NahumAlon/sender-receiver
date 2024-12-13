package telran.producer.consumer;

public class Sender extends Thread {
    private int nMessages;
    private MessageBox messageBoxEven;
    private MessageBox messageBoxOdd;

    public Sender(int nMessages, MessageBox messageBoxEven, MessageBox messageBoxOdd, String name) {
        this.nMessages = nMessages;
        this.messageBoxEven = messageBoxEven;
        this.messageBoxOdd = messageBoxOdd;
        setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < nMessages; i++) {
            MessageBox currentMessageBox = (i + 1) % 2 == 0 ? messageBoxEven : messageBoxOdd;
            try {
                currentMessageBox.put("Message #%d from %s".formatted(i + 1, getName()));
            } catch (InterruptedException e) {
            }
        }
    }   
}
