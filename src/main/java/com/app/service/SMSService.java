package com.app.service;

import com.app.model.SMS;
import org.springframework.stereotype.Service;
import com.app.utils.SMSQueue;

@Service
public class SMSService implements Runnable {

    private final SMSQueue queue = new SMSQueue();
    private boolean enableFlag;
    private int counter = 0;
    private final Thread thread;

    public SMSService() {
        thread = new Thread(this);
        thread.start();
        enableFlag = true;
    }

    @Override
    public void run() {
        while (true) {
            if(enableFlag) {
                try{
                    process();
                } catch (RuntimeException e) {
                    System.out.println(String.format("Following error occurred: %s", e.getMessage()));
                    return;
                }
            } else {
                return;
            }
        }
    }

    public void sendNotificationSMS(SMS message) throws RuntimeException {
        if (enableFlag) {
            queue.add(message);
            synchronized(queue) {
                queue.notify();
            }
        } else
            throw new RuntimeException("Communication through sms is not enabled");
    }

    public int getSentSMS() {
        return counter;
    }

    public void close() {
        System.out.println("Closing communication... ");
        enableFlag = false;
        synchronized(queue) {
            queue.notify();
        }

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void process() {
        SMS message = queue.get();
        if (message != null) {
            send(message);
        } else {
            System.out.println("Waiting for sms...");
            try {
                synchronized (queue) {
                    queue.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void send(SMS message) {
        System.out.println("Following sms has been sent: " + message);
        counter++;
    }
}
