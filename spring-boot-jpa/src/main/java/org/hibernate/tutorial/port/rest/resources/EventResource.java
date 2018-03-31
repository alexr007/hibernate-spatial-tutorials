package org.hibernate.tutorial.port.rest.resources;

import java.time.LocalDateTime;

import org.geolatte.geom.Point;

import org.hibernate.tutorial.domain.model.Event;

/**
 * Created by Karel Maesen, Geovise BVBA on 22/03/2018.
 */
public class EventResource {
	final private String name;
//	final private String description;
//	final private LocalDateTime dateTime;
//	final private Point point;

	public static EventResource fromEvent(Event event) {
		return new EventResource(event.getName());
	}

	public EventResource(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	};

}
