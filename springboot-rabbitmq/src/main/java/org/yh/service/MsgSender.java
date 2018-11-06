package org.yh.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String context) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String context = "hello " + sdf.format(new Date());
		System.out.println("发送消息内容：" + context);
		this.rabbitTemplate.convertAndSend("hello", context);
	}

	public void sendObject(String context) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("code", 1);
		obj.put("context", context);
		System.out.println("发送消息内容：" + obj);
		this.rabbitTemplate.convertAndSend("hello", obj.toString());
	}

	public void topicSend1(String context) {
		System.out.println("发送消息内容：" + context);
		this.rabbitTemplate.convertAndSend("myExchange", "topic.message", context);
	}

	public void topicSend2(String context) {
		System.out.println("发送消息内容：" + context);
		// 第一个参数是交换机名，第二个是路由键(根据这个key,去寻找有没有匹配规则的队列)，第三个是消息内容
		this.rabbitTemplate.convertAndSend("myExchange", "topic.messages", context);
	}

	public void fanout(String context) {
		System.out.println("广播式发送内容：" + context);
		this.rabbitTemplate.convertAndSend("fanoutExchange", "", context);
	}
}
