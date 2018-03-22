package org.hibernate.tutorial.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;

import org.geolatte.geom.Point;

/**
 * Created by Karel Maesen, Geovise BVBA on 08/03/2018.
 */

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String description;
	private LocalDateTime dateTime;
	private Point point;

	public Event(String name, String description, LocalDateTime dateTime, Point point) {
		this.name = name;
		this.description = description;
		this.dateTime = dateTime;
		this.point = point;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}