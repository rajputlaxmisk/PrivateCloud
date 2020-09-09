package com.project1;

import java.util.HashMap;
import java.util.Map;

//import org.apache.kafka.clients.producer;
//import org.springframework.kafka.core;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


import org.springframework.kafka.support.serializer.JsonSerializer;
//import org.springframework.kafka.support.serializer.StringSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
//import org.apache.kafka.common.serialization.JsonSerializer;

//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.ser.std.StringSerializer;

//Java program to serialize the 
//object of the model class 

@Configuration
public class kafkaConf { 

 @Bean
 public ProducerFactory<String, Object> 
 producerFactory() 
 { 
     // Create a map of a string 
     // and object 
     Map<String, Object> config 
         = new HashMap<>(); 

     config.put( 
         ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
         "54.149.221.152:9092"); //1

     config.put( 
         ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
         StringSerializer.class); 

     config.put( 
         ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
         JsonSerializer.class); 

     return new DefaultKafkaProducerFactory<>(config); 
 } 

 @Bean
 public KafkaTemplate<String, Object>    //Object->Map<String, Object>
 kafkaTemplate() 
 { 
     return new KafkaTemplate<>( 
         producerFactory()); 
 } 
} 