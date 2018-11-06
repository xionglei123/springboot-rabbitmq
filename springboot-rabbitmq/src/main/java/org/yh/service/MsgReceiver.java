package org.yh.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class MsgReceiver {

	// rabbitHander 根据消息类型来决定谁来处理
	@RabbitHandler
	public void process(String message) {
		System.out.println("接收到的消息：" + message);
	}

	@RabbitHandler
	public void process2(Integer message) {
		System.out.println("接收2收到的消息：" + message);
	}

}
