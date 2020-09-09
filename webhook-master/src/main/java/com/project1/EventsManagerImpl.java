package com.project1;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class EventsManagerImpl implements EventsManager {

/*	@Override
	public void prometheusAlerts(Map<String, Object> alert) {
		// TODO Auto-generated method stub
		System.out.println("Prometheus WebHook collected JSON: " + alert);

	}
	
*/
	@Autowired
    private KafkaTemplate<String, Object>   //Object->Map<String, Object>
        kafkaTemplate; 
  
    private static final String TOPIC 
        = "test"; //3
        


	@Override
	public Map<String, Object> prometheusAlerts(Map<String, Object> alert)
    {
		kafkaTemplate.send(TOPIC, alert);
		System.out.println("Prometheus WebHook collected JSON: " + alert);
		return alert;
	}

	@Override
	public void otherOpenSourceAlerts(Map<String, Object> alert) {
		System.out.println("Not yet implemented");
		

	}

}
