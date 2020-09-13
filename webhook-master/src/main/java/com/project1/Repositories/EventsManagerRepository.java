package com.project1.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project1.model.Prom;

public interface EventsManagerRepository extends MongoRepository<Prom, Object>{
	/*
	 * List<Tutorial> findByTitleContaining(String title); List<Tutorial>
	 * findByPublished(boolean published);
	 */
	
}







