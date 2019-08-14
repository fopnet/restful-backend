package com.company;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import com.company.place.PlaceApplication;
import com.company.place.exception.PlaceException;
import com.company.place.model.Place;
import com.company.place.repository.PlaceRepository;
import com.company.place.service.PlaceServiceImpl;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = PlaceApplication.class)
public class PlaceServiceTest {

	@InjectMocks
	PlaceServiceImpl placeService;

	@Mock
	PlaceRepository placeRepository;

	@Test
	public void testGetById() {
		Place expected = new Place(1l);

		Mockito.when(placeRepository.findById(1L)).thenReturn(Optional.of(new Place(1l)));

		assertEquals(placeRepository.findById(1l).get(), expected, "Must return place 1");
	}

	@Test
	public void testGetIdNotExists() {

		Mockito.when(placeRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(PlaceException.class, () -> this.placeService.getPlaceById(1l));
	}

	@Test
	public void testGetNameByNameNotExists() {

		@SuppressWarnings("unchecked")
		List<Place> emptyList = Collections.EMPTY_LIST;

		Mockito.when(placeRepository.findByName("pina")).thenReturn(emptyList);

		assertThrows(PlaceException.class, () -> this.placeService.getPlacesByName("pina"));

	}

	@Test()
	public void testGetNameByNameExists() {

		Place p = new Place(1l);
		p.setName("pina");

		List<Place> expected = Arrays.asList(p);

		Mockito.when(placeRepository.findByName("pina")).thenReturn(expected);

		List<Place> result = this.placeService.getPlacesByName("pina");

		assertFalse(result.isEmpty(), "Must not be null");
		assertEquals(result.get(0), p, "Must not be null");
		assertEquals(result.get(0).getName(), p.getName(), "Must not be null");

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

		assertNotNull(result, "Must not be null");
		assertEquals(result, expected, "Must be the extected place");

	}

	@Test
	public void testCreatePlaceNullValues() {

		Place expected = new Place(1l);
		expected.setName(null);
		expected.setSlug("slug");
		expected.setState("RJ");
		expected.setCity("city");

		Mockito.when(placeRepository.save(expected)).thenThrow(TransactionSystemException.class);

		assertThrows(TransactionSystemException.class, () -> this.placeService.createPlace(expected));

	}

	@Test
	public void testUpdatePlace() {

		Place expected = new Place(1l);
		expected.setName("pina");
		expected.setSlug("slug");
		expected.setState("RJ");
		expected.setCity("city");

		Mockito.when(placeRepository.findById(1l)).thenReturn(Optional.of(expected));

		assertEquals(expected.getName(), "pina", "Place name must by pina");

		expected.setName("pina2");

		Mockito.when(placeRepository.save(expected)).thenReturn(expected);

		Place result = this.placeService.updatePlace(expected, 1l);

		assertNotNull(result, "Must not be null");
		assertEquals(result, expected, "Must be the expected place");
		assertEquals(result.getName(), "pina2", "Must be the expected place");

	}

	@Test
	public void testUpdatePlaceNotExists() {

		Place expected = new Place(1l);
		expected.setName("pina");
		expected.setSlug("slug");
		expected.setState("RJ");
		expected.setCity("city");

		Mockito.when(placeRepository.findById(1l)).thenReturn(Optional.empty());

		assertThrows(PlaceException.class, () -> this.placeService.updatePlace(expected, 1l));

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

	@Test
	public void testDeletePlaceNotExists() {

		Mockito.when(placeRepository.findById(1l)).thenReturn(Optional.empty());

		assertThrows(PlaceException.class, () -> placeService.deleteById(1l));

	}

}
