package org.hibernate.tutorial.eventservice.port.rest.resources;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import org.hibernate.tutorial.eventservice.domain.model.Event;

/**
 * A DTO for the EventResource
 *
 * Created by Karel Maesen, Geovise BVBA on 22/03/2018.
 */
public class EventResource {

	private String name;
	private String description;
	private OffsetDateTime dateTime;
	private Point<G2D> point;

	public static EventResource fromEvent(Event event) {
		return new EventResource(event.getName(), event.getDescription(),
								 toOffsetDateTime(event.getDateTime()), event.getPoint());
	}

	public static Event toEvent(EventResource resource) {
		return new Event(resource.getName(), resource.getDescription(),
						 toLocalDateTime(resource.getDateTime()), resource.getPoint());
	}

	public EventResource(){}; //Required for constructing instances from JSON

	public EventResource(String name, String description, OffsetDateTime dateTime, Point<G2D> point) {
		this.name = name;
		this.description = description;
		this.dateTime = dateTime;
		this.point = point;
	}

	public String getName(){
		return name;
	};

	public String getDescription() {
		return description;
	}

	//In REST resources we prefer to use OffsetDateTimes
	public OffsetDateTime getDateTime() {
		return dateTime;
	}

	public Point<G2D> getPoint() {
		return point;
	}

	private static OffsetDateTime toOffsetDateTime(LocalDateTime ldt) {
		if (ldt == null) return null;
		return ldt.atZone( ZoneId.systemDefault()).toOffsetDateTime();
	}

	private static LocalDateTime toLocalDateTime(OffsetDateTime odt) {
		if (odt == null) return null;
		return odt.toLocalDateTime();
	}
}
