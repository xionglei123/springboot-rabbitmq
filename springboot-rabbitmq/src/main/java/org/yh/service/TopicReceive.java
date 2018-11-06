package org.yh.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceive {

	@RabbitListener(queues = "topic.message")
	public void process(String context) {
		System.out.println("队列topic.message接收到的消息：" + context);
	}

	@RabbitListener(queues = "topic.messages")
	public void process2(String context) {
		System.out.println("队列topic.messages接收到的消息：" + context);
	}
}
