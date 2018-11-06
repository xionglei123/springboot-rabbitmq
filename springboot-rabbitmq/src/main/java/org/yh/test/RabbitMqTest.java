package org.yh.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yh.service.MsgSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

	@Autowired
	private MsgSender sender;

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
