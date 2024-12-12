package telran.producer.consumer;

public class Sender extends Thread {
    private int nMessages;
    private MessageBox messageBox;

    public Sender(int nMessages, MessageBox messageBox, String name) {
        this.nMessages = nMessages;
        this.messageBox = messageBox;
        setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < nMessages; i++) {
            try {
                messageBox.put("Message #%d from %s".formatted(i + 1, getName()));
            } catch (InterruptedException e) {
            }
        }
    }   
}
