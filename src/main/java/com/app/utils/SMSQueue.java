package com.app.utils;

import com.app.model.SMS;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SMSQueue {

    private final List<SMS> messages = Collections.synchronizedList(new LinkedList<>());

    public void add(SMS message) {
        messages.add(message);
    }

    public SMS get() {
        if (messages.size() > 0) {
            return messages.remove(messages.size() - 1);
        }

        return null;
    }
}
