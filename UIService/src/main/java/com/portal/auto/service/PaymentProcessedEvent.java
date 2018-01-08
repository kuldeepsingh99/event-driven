package com.portal.auto.service;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.portal.auto.bean.Employee;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@Component
@RabbitListener(queues = "#{autoDeleteQueue1.name}")
public class PaymentProcessedEvent {

    private static Employee employee;
	
    @RabbitHandler
    public static void receiveMessage(Employee emp) {
		System.out.println("Received for Stock Processing <" + emp.getEmpName()+" "+emp.getEmpId() + " "+emp.getQuantity() +">");
		employee = emp;
    }

    public Flux<Employee> getCurrentEmployee() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
        Flux<Employee> stockTransactionFlux = Flux.fromStream(Stream.generate(() -> employee));
        return Flux.zip(interval, stockTransactionFlux).map(Tuple2::getT2);
    }

}
