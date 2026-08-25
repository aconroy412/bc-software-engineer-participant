package com.northstar.crm.config;

import org.apache.kafka.common.TopicPartition;
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

  private static final String CUSTOMER_EVENTS_DLT_SUFFIX = ".DLT";
  @Bean
  public DefaultErrorHandler kafkaErrorHandler(
        KafkaTemplate<Object, Object> template) {

    DeadLetterPublishingRecoverer recoverer =
            new DeadLetterPublishingRecoverer(
                    template,
                    (record, ex) ->
                            new TopicPartition(
                                    record.topic() + CUSTOMER_EVENTS_DLT_SUFFIX,
                                    record.partition()));

    FixedBackOff backoff = new FixedBackOff(1_000L, 3L);

    DefaultErrorHandler handler =
            new DefaultErrorHandler(recoverer, backoff);

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
