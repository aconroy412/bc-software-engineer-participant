package com.northstar.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import com.northstar.crm.event.CustomerEvent;
import com.northstar.crm.event.InvalidCustomerEventException;
import com.northstar.crm.event.UnsupportedEventVersionException;

@Configuration
public class KafkaErrorConfig {

  @Bean
  public CommonErrorHandler kafkaErrorHandler(
          KafkaTemplate<Object, Object> template) {

      DeadLetterPublishingRecoverer recoverer =
              new DeadLetterPublishingRecoverer(template);

      DefaultErrorHandler handler =
              new DefaultErrorHandler(
                      recoverer,
                      new FixedBackOff(1000, 2));

      handler.addNotRetryableExceptions(
              InvalidCustomerEventException.class,
              UnsupportedEventVersionException.class);

      return handler;
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, CustomerEvent>
  kafkaListenerContainerFactory(
          ConsumerFactory<String, CustomerEvent> consumerFactory,
          CommonErrorHandler kafkaErrorHandler) {

      ConcurrentKafkaListenerContainerFactory<String, CustomerEvent> factory =
          new ConcurrentKafkaListenerContainerFactory<>();

      factory.setConsumerFactory(consumerFactory);
      factory.setCommonErrorHandler(kafkaErrorHandler);

      return factory;
  }
}
