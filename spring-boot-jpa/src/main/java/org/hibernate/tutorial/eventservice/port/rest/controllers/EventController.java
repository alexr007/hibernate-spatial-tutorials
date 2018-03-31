package org.hibernate.tutorial.eventservice.port.rest.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.tutorial.eventservice.domain.model.Event;
import org.hibernate.tutorial.eventservice.port.persistence.EventRepository;
import org.hibernate.tutorial.eventservice.port.rest.resources.EventResource;

/**
 * Created by Karel Maesen, Geovise BVBA on 22/03/2018.
 */

@RestController
public class EventController {

	private static final Logger log = LoggerFactory.getLogger( EventController.class );


	final private EventRepository repository;

	public EventController(EventRepository repository){
		this.repository = repository;
	}

	@RequestMapping("/events")
	public List<EventResource> index() {
		log.info("Getting All events");
		Iterable<Event> events = repository.findAll();
		return StreamSupport
				.stream( events.spliterator() , false)
				.map( EventResource::fromEvent )
				.collect(Collectors.toList());
	}

	@RequestMapping(path="/events", method=RequestMethod.POST)
	ResponseEntity<?> post(@RequestBody EventResource resource) {
		log.info("Posting an event");
		Event event = EventResource.toEvent( resource );
		try {
			Event saved = repository.save( event );
			log.info("Saved event: " + saved);
			Long id = saved.getId();
			return ResponseEntity.created( new URI("/events/" + id) ).build();
		} catch(Throwable t) {
			log.error( "Error saving Event", t );
			return ResponseEntity.status( 500 ).body( "Error saving Event, see the logs" );
		}
	}

	@RequestMapping(path="/events/{eventId}")
	ResponseEntity<EventResource> getEventById(@PathVariable String eventId){
		log.info("GETTING event with Id: " + eventId);
		try {
			Optional<Event> eventOpt = repository.findById( Long.parseLong( eventId ) );
			return eventOpt
					.map( EventResource::fromEvent )
					.map( ResponseEntity::ok )
					.orElse( ResponseEntity.notFound().build() );
		} catch(Throwable t) {
			log.error( "Error getting Event", t );
			return ResponseEntity.status( 500 ).build();
		}
	}

}
