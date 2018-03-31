package org.hibernate.tutorial.eventservice.port.rest.resources;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Random;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.geolatte.geom.crs.Geographic2DCoordinateReferenceSystem;

import org.hibernate.tutorial.eventservice.domain.model.Event;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.point;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

/**
 * A DTO for the EventResource
 * <p>
 * Created by Karel Maesen, Geovise BVBA on 22/03/2018.
 */
public class EventResource {

	private Long id;
	private String name;
	private String description;
	private OffsetDateTime dateTime;
	private Point<G2D> point;

	public static EventResource fromEvent(Event event) {
		return new EventResource( event.getId(), event.getName(), event.getDescription(),
								  toOffsetDateTime( event.getDateTime() ), event.getPoint()
		);
	}

	public static Event toEvent(EventResource resource) {
		return new Event( resource.getId(), resource.getName(), resource.getDescription(),
						  toLocalDateTime( resource.getDateTime() ), resource.getPoint()
		);
	}

	public EventResource() {
	}

	; //Required for constructing instances from JSON

	public EventResource(Long id, String name, String description, OffsetDateTime dateTime, Point<G2D> point) {
		this.id  = id;
		this.name = name;
		this.description = description;
		this.dateTime = dateTime;
		this.point = point;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	;

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
		if ( ldt == null ) {
			return null;
		}
		return ldt.atZone( ZoneId.systemDefault() ).toOffsetDateTime();
	}

	private static LocalDateTime toLocalDateTime(OffsetDateTime odt) {
		if ( odt == null ) {
			return null;
		}
		return odt.toLocalDateTime();
	}

	/**
	 * This is only for testing purposes
	 *
	 * @return
	 */
	public static class Gen{
		private static Random random = new Random();
		public EventResource generate() {

			return new EventResource(
					null,
					generateString(10),
					generateString(60),
					OffsetDateTime.now().plusDays( random.nextInt(60) ),
					generatePoint()
			);
		}

		private String generateString(int length) {
			StringBuilder stb = new StringBuilder();
			for ( int i = 0; i < length; i++ ) {
				int ri = random.nextInt( 27 );
				if (ri <= 25) {
					stb.append( Character.toChars( 97 + ri ) );
				} else {
					stb.append(' ');
				}
			}
			return stb.toString();
		}

		private Point<G2D> generatePoint() {
			return point(WGS84, g(randomLon(), randomLat()));
		}

		private double randomLat() {
			return -80.0 + random.nextDouble() * 160.;
		}

		private double randomLon() {
			return -180.0 + random.nextDouble() * 360.;
		}
	}

}
