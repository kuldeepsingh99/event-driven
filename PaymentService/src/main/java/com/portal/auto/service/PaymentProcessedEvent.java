package com.portal.auto.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.portal.auto.bean.Employee;

@Service
public class PaymentProcessedEvent {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${javainuse.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${javainuse.rabbitmq.routingkey}")
	private String routingkey;	
	
	@Autowired
	private TopicExchange topic;
	
	public void send(Employee company) {
		rabbitTemplate.convertAndSend(topic.getName(), "" , company);
		System.out.println("Send msg = " + company);
	}
}
