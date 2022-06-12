# apachemq_jms_eclipseproject
Tutorial ApacheMQ + JMS using Eclipse IDE

How to run this project?
Step 1. Add new java console project

Step 2. Import using existing Maven project

Step 3. Run Application.java

Pre-requisites
==============
Install ApacheMQ
When you have installed this, go to your browser and open http://localhost:8161/admin
default username/password admin/admin

Run Application.java
Open http://localhost:9000/ to populate data from servlet and it will hit to the backend (send and receive service).

com.linkedinjms.chapter3
========================
It's a Send / Receive service using ApacheMQ and a continuation from chapter 2.

The format is using JSON.
We're about to use XML as well however, the latest library XStreamMarshaller is not compatible.
As result, we couldn't test using XML serialization.

Additional Service is WarehouseProcessingService.

The workflow is as below:
From the web interface http://localhost:9000/ add data and data will be queued in book.order.queue.
WarehouseReceiver.java listens messages coming from book.order.queue; then WarehouseReceiver send data to warehouseProcessingService.processOrder(bookOrder);
WarehouseProcessingService sends to book.order.processed.queue and we set up synchronous service to listen this message configured in JmsConfig.java

<b>Below is the custom listener used only in chapter 3</b>
```
//Introduce our listener that we have created BookOrderProcessingMessageListener
	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
        endpoint.setMessageListener(jmsMessageListener());
        endpoint.setDestination("book.order.processed.queue");
        endpoint.setId("book-order-processed-queue");
        endpoint.setConcurrency("1");
        endpoint.setSubscription("my-subscription");
        registrar.registerEndpoint(endpoint, jmsListenerContainerFactory());
        registrar.setContainerFactory(jmsListenerContainerFactory());
		
		/*
		 * By having the configuration above, when we send message
		 * it will process to the warehouse and to the book order process and to 
		 * book border.order.processed.queue and our customer will listen from that queue
		 * */
	}
  ```
![image](https://user-images.githubusercontent.com/1523220/173213440-f44d3d27-1704-429a-a3db-7eac34023e27.png)
