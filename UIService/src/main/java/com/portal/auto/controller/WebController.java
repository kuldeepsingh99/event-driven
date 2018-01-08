package com.portal.auto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portal.auto.bean.Employee;
import com.portal.auto.service.PaymentProcessedEvent;
import com.portal.auto.service.PurchaseRequestedEvent;

import reactor.core.publisher.Flux;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/emp/")
public class WebController {

	@Autowired
	PurchaseRequestedEvent rabbitMQSender;

	@Autowired
	PaymentProcessedEvent paymentProcessedEvent;

	@GetMapping(value = "/producer")
	public Boolean producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {

		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		rabbitMQSender.send(emp);

		return true;
	}

	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/employee" ,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Employee> getEmployee(){
		System.out.println("running ");
		return paymentProcessedEvent.getCurrentEmployee();
	}
}
