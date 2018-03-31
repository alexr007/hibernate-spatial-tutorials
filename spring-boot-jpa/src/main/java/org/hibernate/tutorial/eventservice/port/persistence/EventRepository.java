package org.hibernate.tutorial.eventservice.port.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.hibernate.tutorial.eventservice.domain.model.Event;

/**
 * Created by Karel Maesen, Geovise BVBA on 08/03/2018.
 */
public interface EventRepository extends CrudRepository<Event, Long>{

//	List<Event> findNearBy(Point point, double distance);

	List<Event> findByName(String name);

}
