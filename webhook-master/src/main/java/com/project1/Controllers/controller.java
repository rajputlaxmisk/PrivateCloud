package com.project1.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project1.Repositories.EventsManagerRepository;
import com.project1.model.Prom;

/*import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
*/


@RestController
public class controller {
	
	@Autowired
    private KafkaTemplate<String, Object> 
        kafkaTemplate; 
  
    private static final String TOPIC 
        = "test"; //3
    
    @Autowired
    private EventsManagerRepository repository;
    
	/* MongoClient mongoClient = new MongoClient(); */
    
	@RequestMapping("/")
	public String show()
	{
		return "Hello";
	}
	// Database ; Prom
	//@PostMapping(value="/data" , consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value="/data")
    public Object getProme(@RequestBody Object alert) {
		//alert=json 
    //System.out.println("Prometheus WebHook collected JSON: " + alert);
		//prometheusAlerts(Map<String, Object> json)
		return  prome(alert);
    }
	//database
	public Object prome(Object alert) {
		// TODO Auto-generated method stub
		Prom p=new Prom(alert);
		
		System.out.println("\n Prometheus WebHook collected alert: " + alert);
		repository.save(p);
		
		
		/*
		 * MongoDatabase db = mongoClient.getDatabase("Database");
		 * MongoCollection<org.bson.Document> collection = db.getCollection("Prom");
		 * collection.insertOne((org.bson.Document) alert);
		 */
		
		
		return alert;
	}
	
	
	
	
	
	
	//@PostMapping(value="/webhook", consumes = {"application/json"},produces = {"application/json"})
	@PostMapping(value="/webhook" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> getMyPrometheusAlert(@RequestBody Map<String, Object> alert) {
		//alert=json 
    //System.out.println("Prometheus WebHook collected JSON: " + alert);
		//prometheusAlerts(Map<String, Object> json)
		return prometheusAlerts(alert);
    }
    
	/*
	 * 
	 * actual implementation of private cloud
	 * 
	 * 
	 * //@PostMapping(value="/webhook", consumes = {"application/json"},produces =
	 * {"application/json"})
	 * 
	 * @PostMapping(value="/webhook") public @ResponseBody Map<String, Object>
	 * getMyPrometheusAlert(@RequestBody Map<String, Object> alert) { //alert=json
	 * //System.out.println("Prometheus WebHook collected JSON: " + alert);
	 * //prometheusAlerts(Map<String, Object> json) return prometheusAlerts(alert);
	 * }
	 */
	
	public Map<String, Object> prometheusAlerts(Map<String, Object> alert) {
		// TODO Auto-generated method stub
		
		System.out.println("\n Prometheus WebHook collected alert: " + alert);
		repository.save((Prom)alert);
		kafkaTemplate.send(TOPIC, alert);
		
		
		System.out.println("\n alert send" + alert);
		
		
		return alert;
	}
	

  
 /*   @PostMapping("/webhook") 
  
    public String post(@RequestBody  Map<String, Object> alert) 
    { 
  
        kafkaTemplate.send(TOPIC, alert); 
  
        return "Published successfully"; 
    } 
*/	

}

