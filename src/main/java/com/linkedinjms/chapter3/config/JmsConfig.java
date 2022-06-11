package com.linkedinjms.chapter3.config;

import java.awt.List;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.linkedinjms.chapter3.pojos.Book;
import com.linkedinjms.chapter3.pojos.BookOrder;
import com.linkedinjms.chapter3.pojos.Customer;

// Add annotation EnableJms, so the JmsListener will be picked up.
@EnableJms
//Add the annotation below so that Spring will know that this is part of configuration
@Configuration
public class JmsConfig {
	/*
	 * Remember to use JmsSupportConverterOne
	 * @Bean to tell Spring to prepopulate these config
	 * When enabling xmlMarshallingMessageConverter @Bean and send using XML
	 * This following @Bean should be turned off, otherwise it will throw an error
	 * TURN ON/OFF, if you want to send using JSON
	*/
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		//Use the jms support converter one
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		
		/*
		 * Below is doesn't matter whatever the type id as long as what you set should be set in 
		 * both converter.
		 * */
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		return factory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		
		//Turn on/off if you want to use JSON file format
		factory.setMessageConverter(jacksonJmsMessageConverter());
		
		return factory;
	}
}
