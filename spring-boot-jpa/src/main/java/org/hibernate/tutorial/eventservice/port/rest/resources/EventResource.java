package org.hibernate.tutorial.eventservice.port.rest.resources;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import org.hibernate.tutorial.eventservice.domain.model.Event;

/**
 * Created by Karel Maesen, Geovise BVBA on 22/03/2018.
 */
public class EventResource {
	final private String name;
	final private String description;
	final private OffsetDateTime dateTime;
	final private Point<G2D> point;

	public static EventResource fromEvent(Event event) {
		return new EventResource(event.getName(), event.getDescription(), event.getDateTime(), event.getPoint());
	}

	public EventResource(String name, String description, LocalDateTime dateTime, Point<G2D> point) {
		this.name = name;
		this.description = description;
		this.dateTime = toOffsetDateTime(dateTime);
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

	private OffsetDateTime toOffsetDateTime(LocalDateTime ldt) {
		if (ldt == null) return null;
		return ldt.atZone( ZoneId.systemDefault()).toOffsetDateTime();
	}
}
