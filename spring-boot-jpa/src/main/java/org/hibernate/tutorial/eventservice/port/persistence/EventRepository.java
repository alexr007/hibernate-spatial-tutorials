package org.hibernate.tutorial.eventservice.port.persistence;

import javax.persistence.Tuple;
import java.util.List;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.hibernate.tutorial.eventservice.domain.model.Event;

/**
 * Created by Karel Maesen, Geovise BVBA on 08/03/2018.
 */
public interface EventRepository extends CrudRepository<Event, Long>{

//	List<Event> findNearBy(Point point, double distance);

	List<Event> findByName(String name);

	@Query("SELECT evt, distance(evt.point, ?1) as distance from Event evt")
	List<Tuple> findAllWithDistance(Point<G2D> refPnt);
}
