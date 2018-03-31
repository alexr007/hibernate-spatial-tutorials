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

	/**
	 * Ensure that the geolatte geojson module is added to the Json ObjectMapper
	 * @return
	 */
	@Bean
	public Module geoJsonModule(){
		return new org.geolatte.geom.json.GeolatteGeomModule();
	}

}