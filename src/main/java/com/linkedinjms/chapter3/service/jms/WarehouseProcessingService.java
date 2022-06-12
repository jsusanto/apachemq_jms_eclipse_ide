package com.linkedinjms.chapter3.service.jms;

import com.linkedinjms.chapter3.pojos.BookOrder;
import com.linkedinjms.chapter3.pojos.ProcessedBookOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WarehouseProcessingService {
	private static final String BOOK_ORDER = "book.order.processed.queue";
	
    @Autowired
    private JmsTemplate jmsTemplate;

    public void processOrder(BookOrder bookOrder){
        ProcessedBookOrder order = new ProcessedBookOrder(
                bookOrder,
                new Date(),
                new Date()
        );
        jmsTemplate.convertAndSend(BOOK_ORDER, bookOrder);
    }

}
