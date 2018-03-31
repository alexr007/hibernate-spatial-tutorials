package org.hibernate.tutorial.eventservice.port.rest.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.hibernate.tutorial.eventservice.port.rest.resources.EventResource;

/**
 * Created by Karel Maesen, Geovise BVBA on 31/03/2018.
 */
@RestController
public class GenerateEventController {

	@RequestMapping(path="/generate", method=RequestMethod.GET)
	public EventResource generateEvent() {
		return new EventResource.Gen().generate();
	}

}
