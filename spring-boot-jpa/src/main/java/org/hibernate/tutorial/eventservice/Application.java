package org.hibernate.tutorial.eventservice;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.Module;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.geolatte.geom.crs.Geographic2DCoordinateReferenceSystem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.tutorial.eventservice.domain.model.Event;
import org.hibernate.tutorial.eventservice.port.persistence.EventRepository;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.point;


@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger( Application.class );


	public static void main(String[] args) throws Exception {
		SpringApplication.run( Application.class, args );
	}

	@Bean
	public Module geoJsonModule(){
		return new org.geolatte.geom.json.GeolatteGeomModule();
	}

	@Bean
	public CommandLineRunner demo(EventRepository repository) {
		return (args) -> {
			// save a couple of customers
			Geographic2DCoordinateReferenceSystem crs = CoordinateReferenceSystems.WGS84;
			repository.save( new Event(
					"Spring One Conference",
					"Pivotal",
					LocalDateTime.now(),
					point( crs, g( 3.35, 53.24 ) )
			) );
			repository.save( new Event(
					"Java One Conference",
					"Oracle",
					LocalDateTime.now(),
					point( crs, g( -54.35, 53.24 ) )
			) );


			// fetch all customers
			log.info( "Customers found with findAll():" );
			log.info( "-------------------------------" );
			for ( Event event : repository.findAll() ) {
				log.info( event.toString() );
			}
			log.info( "" );

			// fetch an individual customer by ID
			repository.findById( 1L )
					.ifPresent( evt -> {
						log.info( "Event found with findById(1L):" );
						log.info( "--------------------------------" );
						log.info( evt.toString() );
						log.info( "" );
					} );

			// fetch customers by last name
			log.info( "Find by Name the 'Java One Conference' " );
			log.info( "--------------------------------------------" );
			repository.findByName( "Java One Conference" ).forEach( javone -> {
				log.info( javone.toString() );
			} );

			log.info( "" );
		};
	}


}