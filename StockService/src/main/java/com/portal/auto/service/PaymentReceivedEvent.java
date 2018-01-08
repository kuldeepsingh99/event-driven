package com.portal.auto.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.portal.auto.bean.Employee;

@Component
@RabbitListener(queues = "#{autoDeleteQueue1.name}")
public class PaymentReceivedEvent {

	
	@RabbitHandler
    public void receiveMessage(Employee emp) {
        System.out.println("Received for Stock Processing <" + emp.getEmpName()+" "+emp.getEmpId() + " "+emp.getQuantity() +">");
        
        // do some processing for Stock Processing
        
        
    }

}
