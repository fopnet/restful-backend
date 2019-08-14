package com.clickbus.place.service;

import java.util.List;

import com.clickbus.place.model.Place;

public interface PlaceService {

	Place createPlace(Place place);
	
	Place getPlaceById(Long id);
	
	Place updatePlace(Place newPlace, Long id);

	List<Place> getPlacesByName(String name);
	
	void deleteById(Long id);

}
