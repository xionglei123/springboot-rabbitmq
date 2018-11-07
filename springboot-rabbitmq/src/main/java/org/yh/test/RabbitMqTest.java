package org.yh.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yh.service.MsgSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

	@Autowired
	private MsgSender sender;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void createExchange() {
		amqpAdmin.declareExchange(new DirectExchange("amqp.exchange"));
		System.out.println("创建一个direct交换机完成");
	}

	@Test
	public void createQueue() {
		// String x = amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
		// System.out.println("创建一个queue完成" + x);
		amqpAdmin.declareBinding(
				new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqp.exchange", "amqp.mytest", null));
		System.out.println("绑定成功");
	}
	
	@Test
	public void delete() {
		amqpAdmin.deleteQueue("fanout.C");
		System.out.println("删除队列成功！");
	}

	@Test
	public void hello() throws Exception {
		sender.sendObject("你看那个人，他好像一条狗哎！");
	}

	@Test
	public void topic() throws Exception {
		sender.topicSend1("topic测试：相视而笑，莫逆于心！");
	}

	@Test
	public void topic2() throws Exception {
		sender.topicSend2("topic2测试：大漠孤烟直，长河落日圆！");
	}

	@Test
	public void fanout() throws Exception {
		sender.fanout("广播：狼来了。。");
	}
}
