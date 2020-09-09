package com.project1;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;



@RestController
public class controller {
	
	@Autowired
    private KafkaTemplate<String, Object> 
        kafkaTemplate; 
  
    private static final String TOPIC 
        = "test"; //3
        

    
	@RequestMapping("/")
	public String show()
	{
		return "Hello";
	}
	
	
	
	@PostMapping(value="/webhook" )
    public @ResponseBody String getMyPrometheusAlert(@RequestBody String alert) {
		//alert=json 
    System.out.println("Prometheus WebHook collected JSON: " + alert);
		//prometheusAlerts(Map<String, Object> json)
		return prometheusAlerts(alert);
    }
	
	
	
	
/*	
	ACTUAL IMPLEMENTATION FOR PROMETHEUS
	
	//@PostMapping(value="/webhook", consumes = {"application/json"},produces = {"application/json"})
	@PostMapping(value="/webhook")
    public @ResponseBody Map<String, Object> getMyPrometheusAlert(@RequestBody Map<String, Object> alert) {
		//alert=json 
    //System.out.println("Prometheus WebHook collected JSON: " + alert);
		//prometheusAlerts(Map<String, Object> json)
		return prometheusAlerts(alert);
    }
    */
	
	
	
/*	
	ACTUAL CODE FOR PRIVATE CLOUD
	
	
	public Map<String, Object> prometheusAlerts(Map<String, Object> alert) {
		// TODO Auto-generated method stub
		
		System.out.println("\n Prometheus WebHook collected alert: " + alert);
		
		kafkaTemplate.send(TOPIC, alert);
		
		System.out.println("\n alert send" + alert);
		
		
		return alert;
	}
	
*/
  
/*  @PostMapping("/webhook") 
  
    public String post(@RequestBody  Map<String, Object> alert) 
    { 
  
        kafkaTemplate.send(TOPIC, alert); 
  
        return "Published successfully"; 
    } 
*/	

	public String prometheusAlerts(String alert) {
		// TODO Auto-generated method stub
		
		System.out.println("\n Prometheus WebHook collected alert: " + alert);
		
		kafkaTemplate.send(TOPIC, alert);
		
		System.out.println("\n alert send" + alert);
		
		
		return alert;
	}


}

