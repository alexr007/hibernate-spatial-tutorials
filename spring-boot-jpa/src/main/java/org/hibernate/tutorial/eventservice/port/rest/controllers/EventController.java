package org.hibernate.tutorial.eventservice.port.rest.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.hibernate.tutorial.eventservice.domain.model.Event;
import org.hibernate.tutorial.eventservice.port.persistence.EventRepository;
import org.hibernate.tutorial.eventservice.port.rest.resources.EventResource;

/**
 * Created by Karel Maesen, Geovise BVBA on 22/03/2018.
 */

@RestController
public class EventController {


	final private EventRepository repository;

	public EventController(EventRepository repository){
		this.repository = repository;
	}

	@RequestMapping("/events")
	public List<EventResource> index() {
		Iterable<Event> events = repository.findAll();
		return StreamSupport
				.stream( events.spliterator() , false)
				.map( EventResource::fromEvent )
				.collect(Collectors.toList());
	}
}
