package com.project1.Interfaces;

import java.util.Map;

public interface EventsManager 
{
	
	public Map<String, Object> prometheusAlerts(Map<String, Object> alert);
	public void otherOpenSourceAlerts(Map<String, Object> alert);
	
	
	
}
