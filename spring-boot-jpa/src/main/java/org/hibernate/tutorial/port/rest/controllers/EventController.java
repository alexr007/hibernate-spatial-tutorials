package org.hibernate.tutorial.port.rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.hibernate.tutorial.port.rest.resources.EventResource;

/**
 * Created by Karel Maesen, Geovise BVBA on 22/03/2018.
 */

@RestController
public class EventController {


	@RequestMapping("/event")
	public EventResource index() {
		return new EventResource("Intro to Spring Boot");
	}
}
