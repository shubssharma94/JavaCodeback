package com.suhas.springboot.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue sharedQueue;
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    public Producer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        //PRODUCE THE ELEMENTS FROM HERE
        //sharedQueue.put(10);
    }
}


