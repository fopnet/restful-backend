package com.clickbus.place.controller;

import java.util.List;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clickbus.place.exception.ExceptionUtil;
import com.clickbus.place.exception.PlaceException;
import com.clickbus.place.model.Place;
import com.clickbus.place.service.PlaceService;

@RestController
@RequestMapping("v1/places")
public class PlaceController {

	@Autowired
	private PlaceService service;

	@GetMapping("/{id}")
	final Place getPlaceById(@PathVariable("id") Long id) {
		return this.service.getPlaceById(id);
	}

	@GetMapping("/place/{name}")
	final List<Place> getPlaceByName(@PathVariable("name") String name) {
		return this.service.getPlacesByName(name);
	}

	@PostMapping(value = "/")
	final Place createPlace(@RequestBody Place p) {
		return this.service.createPlace(p);
	}

	@PutMapping("/{id}")
	final Place updatePlace(@RequestBody Place p, @PathVariable("id") Long id) {
		return this.service.updatePlace(p, id);
	}

	@DeleteMapping("/{id}")
	final void deletePlace(@PathVariable("id") Long id) {
		this.service.deleteById(id);
	}

	@ResponseBody
	@ExceptionHandler({ PlaceException.class, RollbackException.class, ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String placeErrorHandler(Exception ex) {

		ConstraintViolationException cv = ExceptionUtil.searchForConstraintException(ex);
		if (cv != null) {
			String msg = ExceptionUtil.parseConstraintExceptionMessage(cv);
			return msg;
		} else {
			return ex.getMessage();
		}
	}

}
