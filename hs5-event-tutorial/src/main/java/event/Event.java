package event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

import com.vividsolutions.jts.geom.Point;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Karel Maesen, Geovise BVBA
 * creation-date: 6/18/12
 */
@Entity
public class Event {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	private String title;

	private Date date;

	private Point location;

	public Event() {
	}


	public Event(String title, Date theDate, Point geom) {
		this.title = title;
		this.date = theDate;
		this.location = geom;
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Point getLocation() {
		return this.location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Event{" +
				"id=" + id +
				", title='" + title + '\'' +
				", date=" + date +
				", location=" + location +
				'}';
	}
}