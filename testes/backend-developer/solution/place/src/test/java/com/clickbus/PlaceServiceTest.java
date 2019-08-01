package com.clickbus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.TransactionSystemException;

import com.clickbus.place.exception.PlaceException;
import com.clickbus.place.model.Place;
import com.clickbus.place.repository.PlaceRepository;
import com.clickbus.place.service.PlaceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PlaceServiceTest {

	@InjectMocks
	PlaceServiceImpl placeService;

	@Mock
	PlaceRepository placeRepository;

	@Test
	public void testGetById() {
		Place expected = new Place(1l);

		Mockito.when(placeRepository.findById(1L)).thenReturn(Optional.of(new Place(1l)));

		assertEquals("Deveria retornar o place 1", placeRepository.findById(1l).get(), expected);
	}

	@Test(expected = PlaceException.class)
	public void testGetIdNotExists() {

		Mockito.when(placeRepository.findById(1L)).thenReturn(Optional.empty());

		this.placeService.getPlaceById(1l);

	}
	
	@Test(expected = PlaceException.class)
	public void testGetNameByNameNotExists() {
		
		@SuppressWarnings("unchecked")
		List<Place> emptyList = Collections.EMPTY_LIST;
		
		Mockito.when(placeRepository.findByName("pina")).thenReturn(emptyList);
		
		this.placeService.getPlacesByName("pina");
		
	}
	
	@Test()
	public void testGetNameByNameExists() {
		
		Place p = new Place(1l);
		p.setName("pina");
		
		List<Place> expected = Arrays.asList(p);
		
		Mockito.when(placeRepository.findByName("pina")).thenReturn(expected);
		
		List<Place> result = this.placeService.getPlacesByName("pina");
		
		assertFalse("Must not be null", result.isEmpty());
		assertEquals("Must not be null", result.get(0), p);
		assertEquals("Must not be null", result.get(0).getName(), p.getName());
		
	}
	
	@Test()
	public void testCreatePlace() {
		
		Place expected = new Place(1l);
		expected.setName("pina");
		expected.setSlug("slug");
		expected.setState("RJ");
		expected.setCity("city");
		
		
		Mockito.when(placeRepository.save(expected)).thenReturn(expected);
		
		Place result = this.placeService.createPlace(expected);
		
		assertNotNull("Must not be null", result);
		assertEquals("Must be the extected place", result, expected);
		
	}
	
	@Test(expected=TransactionSystemException.class)
	public void testCreatePlaceNullValues() {
		
		Place expected = new Place(1l);
		expected.setName(null);
		expected.setSlug("slug");
		expected.setState("RJ");
		expected.setCity("city");
		
		
		Mockito.when(placeRepository.save(expected)).thenThrow(TransactionSystemException.class);
		
		this.placeService.createPlace(expected);
		
	}
	
	@Test
	public void testUpdatePlace() {
		
		Place expected = new Place(1l);
		expected.setName("pina");
		expected.setSlug("slug");
		expected.setState("RJ");
		expected.setCity("city");
		
		
		Mockito.when(placeRepository.findById(1l)).thenReturn(Optional.of(expected));
		
		assertEquals("Plane name must by pina", expected.getName(), "pina");
		
		expected.setName("pina2");
		
		Mockito.when(placeRepository.save(expected)).thenReturn(expected);
		
		Place result = this.placeService.updatePlace(expected, 1l);
		
		assertNotNull("Must not be null", result);
		assertEquals("Must be the expected place", result, expected);
		assertEquals("Must be the expected place", result.getName(), "pina2");
		
	}
	
	
	@Test(expected=PlaceException.class)
	public void testUpdatePlaceNotExists() {
		
		Place expected = new Place(1l);
		expected.setName("pina");
		expected.setSlug("slug");
		expected.setState("RJ");
		expected.setCity("city");
		
		
		Mockito.when(placeRepository.findById(1l)).thenReturn(Optional.empty());
		
		this.placeService.updatePlace(expected, 1l);
		
	}
	
	@Test
	public void testDeletePlace() {
		
		Place expected = new Place(1l);
		expected.setName("pina");
		expected.setSlug("slug");
		expected.setState("RJ");
		expected.setCity("city");
		
		
		Mockito.when(placeRepository.findById(1l)).thenReturn(Optional.of(expected));
		
		placeService.deleteById(1l);
		
	}

	@Test(expected=PlaceException.class)
	public void testDeletePlaceNotExists() {
		
		Mockito.when(placeRepository.findById(1l)).thenReturn(Optional.empty());
		
		placeService.deleteById(1l);
		
	}

}
