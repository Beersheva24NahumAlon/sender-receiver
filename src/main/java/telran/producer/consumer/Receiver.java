package telran.producer.consumer;

import java.util.regex.*;

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
                int messageNumber = getNumber(msg);
                int receiverNumber = getNumber(getName());
                if (isNeedToProcess(messageNumber, receiverNumber)) {
                    System.out.println("%s received message: %s".formatted(getName(), msg));
                } else {
                    messageBox.put(msg);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private int getNumber(String msg) {
        Matcher matcher = Pattern.compile("#([0-9]+)").matcher(msg);
        String res = "";
        if (matcher.find()) {
            res = matcher.group(1);
        }
        return Integer.parseInt(res);
    }

    private boolean isNeedToProcess(int messageNumber, int receiverNumber) {
        return (messageNumber % 2 == 0 && receiverNumber % 2 == 0)
                || (messageNumber % 2 != 0 && receiverNumber % 2 != 0);
    }

}
