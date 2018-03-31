package org.hibernate.tutorial.eventservice.port.rest.resources;

import javax.persistence.Tuple;

import org.hibernate.tutorial.eventservice.domain.model.Event;

/**
 * Created by Karel Maesen, Geovise BVBA on 31/03/2018.
 */
public class EventResourceWithDistance {

	private EventResource event;
	private Double distanceInMeter;

	public static EventResourceWithDistance from(Tuple tuple) {
		return new EventResourceWithDistance(
				EventResource.fromEvent( (Event) tuple.get( 0 ) ), (Double) tuple.get( 1 ) );
	}

	public EventResourceWithDistance() {
	}

	public EventResourceWithDistance(EventResource event, Double distanceInMeter) {
		this.event = event;
		this.distanceInMeter = distanceInMeter;
	}

	public EventResource getEvent() {
		return event;
	}

	public void setEvent(EventResource event) {
		this.event = event;
	}

	public Double getDistanceInMeter() {
		return distanceInMeter;
	}

	public void setDistanceInMeter(Double distanceInMeter) {
		this.distanceInMeter = distanceInMeter;
	}
}
