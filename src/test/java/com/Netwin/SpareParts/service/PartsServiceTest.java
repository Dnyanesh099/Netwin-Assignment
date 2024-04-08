package com.Netwin.SpareParts.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Netwin.SpareParts.model.Category;
import com.Netwin.SpareParts.model.Make;
import com.Netwin.SpareParts.model.Model;
import com.Netwin.SpareParts.model.Parts;
import com.Netwin.SpareParts.repository.PartsRepository;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class PartsServiceTest {

	@Mock
	private PartsRepository partsRepository;

	@InjectMocks
	private PartsService partsService;

	@Before
	public void setUp() {
		Category category = new Category();
		category.setCategoryName("Sample Category");

		Make make = new Make();
		make.setMakeName("Sample Make");

		Model model = new Model();
		model.setMake(make);
		model.setModelName("Sample Model");

		Parts samplePart = new Parts();
		samplePart.setModel(model);
		samplePart.setCategory(category);
		samplePart.setPartTitle("Sample Part");
		samplePart.setPartPrice(100.0);

		when(partsRepository.findById(1)).thenReturn(Optional.of(samplePart));
		when(partsRepository.findByPartTitleContainingIgnoreCase("Sample")).thenReturn(List.of(samplePart));
	}

	@Test
	public void testAddPart() {
		Parts samplePart = createSamplePart();
		partsService.addPart(samplePart);
		verify(partsRepository, times(1)).save(samplePart);
	}

	@Test
	public void testEditPart() {
		Parts samplePart = createSamplePart();
		Parts updatedPart = createSamplePart();
		updatedPart.setPartTitle("Updated Part");
		updatedPart.setPartPrice(200.0);
		partsService.editPart(1, updatedPart);
		assertEquals("Updated Part", samplePart.getPartTitle());
		assertEquals(200.0, samplePart.getPartPrice(), 0.01);
	}

	@Test
	public void testDeletePart() {
		partsService.deletePart(1);
		verify(partsRepository, times(1)).deleteById(1);
	}

	@Test
	public void testGetAllParts() {
		Parts samplePart = createSamplePart();
		List<Parts> partsList = List.of(samplePart);
		when(partsRepository.findAll()).thenReturn(partsList);
		List<Parts> retrievedParts = partsService.getAllParts();
		assertNotNull(retrievedParts);
		assertEquals(1, retrievedParts.size());
		assertEquals(samplePart, retrievedParts.get(0));
	}

	@Test
	public void testSearchPartsByTitle() {
		Parts samplePart = createSamplePart();
		List<Parts> partsList = List.of(samplePart);
		List<Parts> retrievedParts = partsService.searchPartsByTitle("Sample");
		assertNotNull(retrievedParts);
		assertEquals(1, retrievedParts.size());
		assertEquals(samplePart, retrievedParts.get(0));
	}

	@After
	public void tearDown() {
		reset(partsRepository);
	}

	private Parts createSamplePart() {
		Category category = new Category();
		category.setCategoryName("Sample Category");

		Make make = new Make();
		make.setMakeName("Sample Make");

		Model model = new Model();
		model.setMake(make);
		model.setModelName("Sample Model");

		Parts samplePart = new Parts();
		samplePart.setModel(model);
		samplePart.setCategory(category);
		samplePart.setPartTitle("Sample Part");
		samplePart.setPartPrice(100.0);
		return samplePart;
	}
}
