package com.linkedinjms.chapter3.service.jms;

import com.linkedinjms.chapter3.pojos.BookOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class WarehouseReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseReceiver.class);

    @Autowired
    private WarehouseProcessingService warehouseProcessingService;

    @JmsListener(destination = "book.order.queue")
    public void receive(BookOrder bookOrder){
        LOGGER.info("Message received!");
        LOGGER.info("Message is == " + bookOrder);
        warehouseProcessingService.processOrder(bookOrder);
    }
}
