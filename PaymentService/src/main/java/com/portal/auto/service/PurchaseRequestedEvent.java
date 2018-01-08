package com.portal.auto.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.portal.auto.bean.Employee;

@Component
@RabbitListener(queues = "purchase")
public class PurchaseRequestedEvent {

	public static int quantity = 1000;
	
	@Autowired
	PaymentProcessedEvent paymentEvent;
	
	@RabbitHandler
    public void receiveMessage(Employee emp) {
        System.out.println("Received <" + emp.getEmpName()+" "+emp.getEmpId() + ">");
        
        // do some processing here
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        emp.setQuantity(quantity--);
        paymentEvent.send(emp);
    }

}
