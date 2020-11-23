package com.suhas.springboot.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private final BlockingQueue sharedQueue;
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    public Consumer (BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                LOGGER.info("Consumed: "+ sharedQueue.take());
            } catch (InterruptedException ex) {
                LOGGER.error("Error while consuming a element", ex);
            }
        }
    }
}

