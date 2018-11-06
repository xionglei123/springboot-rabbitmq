package org.yh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

	@Bean(name = "Amessage")
	public Queue AMessage() {
		return new Queue("fanout.A");
	}

	@Bean(name = "Bmessage")
	public Queue BMessage() {
		return new Queue("fanout.B");
	}

	@Bean(name = "Cmessage")
	public Queue CMessage() {
		return new Queue("fanout.C");
	}

	// 配置广播路由器
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}

	@Bean
	Binding bindingExchangeA(@Qualifier("Amessage") Queue Amessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(Amessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeB(@Qualifier("Bmessage") Queue Bmessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(Bmessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeC(@Qualifier("Cmessage") Queue Cmessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(Cmessage).to(fanoutExchange);
	}
}
