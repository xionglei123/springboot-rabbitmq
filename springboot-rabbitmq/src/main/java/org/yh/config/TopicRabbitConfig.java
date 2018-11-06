package org.yh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {
	final static String message = "topic.message";
	final static String messages = "topic.messages";

	@Bean(name = "message")
	public Queue queueMessagae() {
		return new Queue(TopicRabbitConfig.message);
	}

	@Bean(name = "messages")
	public Queue queueMessagaes() {
		return new Queue(TopicRabbitConfig.messages);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("myExchange");
	}

	@Bean
	Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
	}

	@Bean
	Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
	}
}
