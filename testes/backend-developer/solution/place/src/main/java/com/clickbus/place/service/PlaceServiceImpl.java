package com.clickbus.place.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickbus.place.exception.PlaceException;
import com.clickbus.place.model.Place;
import com.clickbus.place.repository.PlaceRepository;

@Service
@Transactional(readOnly = true)
public class PlaceServiceImpl implements PlaceService {

	private final PlaceRepository repo;
	
	public PlaceServiceImpl(PlaceRepository repo) {
		this.repo = repo;
	}
	
	@Override
	@Transactional
	public Place createPlace(Place place) {
		return this.repo.save(place);
	}

	@Override
	public Place getPlaceById(Long id) {
		return this.repo.findById(id).orElseThrow(() -> new PlaceException("Could not find place by id " + id));
	}

	@Override
	@Transactional
	public Place updatePlace(Place newPlace, Long id) {
		return this.repo.findById(id)
		.map(p -> {
			newPlace.setId(p.getId());
			return this.repo.save(newPlace); 
		
		}).orElseThrow(() -> new PlaceException("Place " + id + " does not exists"));
	
	}

	@Override
	public List<Place> getPlacesByName(String name) {
		
		List<Place> places = this.repo.findByName(name);
		
		if (places.isEmpty()) {
			throw new PlaceException("No places found by name " + name);
		}
		
		return places;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		if (this.repo.findById(id).isPresent() ) {
			this.repo.deleteById(id);
		} else {
			throw new PlaceException("Place id " +  id  + " does not exists");
		}
		
	}
	
		

}
