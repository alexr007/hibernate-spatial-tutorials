package event;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import util.JPAUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Karel Maesen, Geovise BVBA
 */
public class EventManager {

	private static final Logger logger = LoggerFactory.getLogger( EventManager.class );

	private final EntityManager entityManager;

	public static void main(String[] args) {
		EventManager mgr = new EventManager();
		String action = args[0];
		try {
			if ( action.equals( "store" ) ) {
				storeEvent( args, mgr );
			}
			else if ( action.equals( "find" ) ) {
				findEvents( args, mgr );
			}
		} catch (Throwable t) {
			logger.error( "Exception on " + action, t );
		} finally{
			mgr.close();
			JPAUtil.close();
		}

	}

	private static void findEvents(String[] args, EventManager mgr) {
		List events = mgr.find( assemble( args ) );
		for ( int i = 0; i < events.size(); i++ ) {
			Event event = (Event) events.get( i );
			System.out.println( "Event: " + event.getTitle() +
										", Time: " + event.getDate() +
										", Location: " + event.getLocation() );
		}
	}

	private static void storeEvent(String[] args, EventManager mgr) {
		mgr.storeEvent( "My Event", new Date(), assemble( args ) );
	}

	public EventManager(){
		this.entityManager = JPAUtil.createEntityManager();
	}

	public void close(){
		try {
			this.entityManager.close();
		} catch(Throwable t){
			// do nothing
		}
	}

	public List find(String wktFilter) {
		Geometry filter = wktToGeometry( wktFilter );
		logger.info("Filtering with filter geom: " + filter);
		Query query = entityManager.createQuery( "select e from Event e where within(e.location, :filter) = true", Event.class );
		query.setParameter( "filter", filter );
		List resultList = query.getResultList();
		return resultList;
	}

	public void storeEvent(String title, Date theDate, String wktPoint) {
		Event theEvent = createEvent( title, theDate, wktPoint );
		persistEvent( theEvent );
	}

	private void persistEvent(Event theEvent) {
		logger.info("Storing event: " + theEvent);
		entityManager.getTransaction().begin();
		try {
			entityManager.persist( theEvent );
			entityManager.getTransaction().commit();
		} catch (Throwable t) {
			entityManager.getTransaction().rollback();
			throw t;
		}
	}

	private Event createEvent(String title, Date theDate, String wktPoint) {
		Geometry geom = wktToGeometry( wktPoint );
		if ( !geom.getGeometryType().equals( "Point" ) ) {
			throw new RuntimeException( "Geometry must be a point. Got a " + geom.getGeometryType() );
		}
		return new Event(title, theDate, (Point)geom);
	}

	private Geometry wktToGeometry(String wktPoint) {
		logger.debug("Receiving string: " + wktPoint);
		WKTReader fromText = new WKTReader();
		Geometry geom = null;
		try {
			geom = fromText.read( wktPoint );
		}
		catch (ParseException e) {
			throw new RuntimeException( "Not a WKT string:" + wktPoint );
		}
		return geom;
	}

	/**
	 * Utility method to assemble all arguments save the first into a String
	 */
	private static String assemble(String[] args) {
		StringBuilder builder = new StringBuilder();
		for ( int i = 1; i < args.length; i++ ) {
			builder.append( args[i] ).append( " " );
		}
		return builder.toString();
	}

}
