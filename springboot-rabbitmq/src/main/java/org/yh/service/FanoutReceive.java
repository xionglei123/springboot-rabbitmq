package org.yh.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceive {

	@RabbitListener(queues = "fanout.A")
	public void processA(String message) {
		System.out.println("A接收ReceiveA:" + message);
	}

	@RabbitListener(queues = "fanout.B")
	public void processB(String message) {
		System.out.println("B接收ReceiveB:" + message);
	}

	@RabbitListener(queues = "fanout.C")
	public void processC(String message) {
		System.out.println("C接收ReceiveC:" + message);
	}
}
