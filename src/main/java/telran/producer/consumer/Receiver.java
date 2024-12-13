package telran.producer.consumer;

public class Receiver extends Thread {
    private MessageBox messageBox;

    public Receiver(MessageBox messageBox, String name) {
        this.messageBox = messageBox;
        setName(name);
    }

    @Override
    public void run() {
        while(true) {
            try {
                String msg = messageBox.take();
                System.out.println("%s received message: %s".formatted(getName(), msg));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
