package com.Netwin.SpareParts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Netwin.SpareParts.model.Parts;
import com.Netwin.SpareParts.service.PartsService;

@RestController
@RequestMapping("/Spareparts")
public class PartsController {

	@Autowired
	private PartsService partsService;

	@PostMapping("/add")
	public ResponseEntity<String> addPart(@RequestBody Parts part) {
		partsService.addPart(part);
		return ResponseEntity.status(HttpStatus.CREATED).body("Part added successfully");
	}

	@PutMapping("/edit/{partId}")
	public ResponseEntity<String> editPart(@PathVariable int partId, @RequestBody Parts part) {
		partsService.editPart(partId, part);
		return ResponseEntity.ok("Part edited successfully");
	}

	@DeleteMapping("/delete/{partId}")
	public ResponseEntity<String> deletePart(@PathVariable int partId) {
		partsService.deletePart(partId);
		return ResponseEntity.ok("Part deleted successfully");
	}

	@GetMapping("/list")
	public ResponseEntity<Iterable<Parts>> getAllParts() {
		Iterable<Parts> parts = partsService.getAllParts();
		return ResponseEntity.ok(parts);
	}

	@GetMapping("/getPartID/{partId}")
	public ResponseEntity<Parts> getPartById(@PathVariable int partId) {
		Parts part = partsService.getPartById(partId);
		if (part != null) {
			return ResponseEntity.ok(part);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/search")
	public ResponseEntity<Iterable<Parts>> searchParts(@RequestParam String title) {
		Iterable<Parts> parts = partsService.searchPartsByTitle(title);
		return ResponseEntity.ok(parts);
	}

	@GetMapping("/findByCategory/{categoryId}")
	public ResponseEntity<List<Parts>> findPartsByCategory(@PathVariable int categoryId) {
		List<Parts> parts = partsService.findPartsByCategoryNative(categoryId);
		return ResponseEntity.ok(parts);
	}

	@GetMapping("/findByModel/{modelId}")
	public ResponseEntity<List<Parts>> findPartsByModel(@PathVariable int modelId) {
		List<Parts> parts = partsService.findPartsByModelNative(modelId);
		return new ResponseEntity<>(parts, HttpStatus.OK);
	}

	// API to update part price by part ID using native query
	@PutMapping("/updatePrice/{partId}")
	public ResponseEntity<String> updatePartPrice(@PathVariable int partId, @RequestParam double partPrice) {
		int updatedRows = partsService.updatePartPriceNative(partId, partPrice);
		if (updatedRows > 0) {
			return ResponseEntity.ok("Part price updated successfully");
		} else {
			return ResponseEntity.ok("Part not found");
		}

	}

	// API to find parts with a price greater than the specified amount using native
	// query
	@GetMapping("/findByPriceGreaterThan/{price}")
	public ResponseEntity<List<Parts>> findPartsByPriceGreaterThan(@PathVariable double price) {
		List<Parts> parts = partsService.findPartsByPriceGreaterThan(price);
		return new ResponseEntity<>(parts, HttpStatus.OK);
	}

	// API to find parts with a price less than the specified amount using native
	// query
	@GetMapping("/findByPriceLessThan/{price}")
	public ResponseEntity<List<Parts>> findPartsByPriceLessThan(@PathVariable double price) {
		List<Parts> parts = partsService.findPartsByPriceLessThanNative(price);
		return new ResponseEntity<>(parts, HttpStatus.OK);
	}

	
	@GetMapping("/findByPriceRange/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Parts>> findPartsByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        List<Parts> parts = partsService.findPartsByPriceRangeNative(minPrice, maxPrice);
        return ResponseEntity.ok(parts);
    }

    // API to find parts with a specific title containing a keyword using native query
    @GetMapping("/findByTitleContaining/{keyword}")
    public ResponseEntity<List<Parts>> findPartsByTitleContaining(@PathVariable String keyword) {
        List<Parts> parts = partsService.findPartsByTitleContainingNative(keyword);
        return ResponseEntity.ok(parts);
    }

    // API to delete parts by category ID using native query
    @DeleteMapping("/deleteByCategory/{categoryId}")
    public ResponseEntity<String> deletePartsByCategory(@PathVariable int categoryId) {
    
            int deletedCount = partsService.deletePartsByCategoryNative(categoryId);
            return ResponseEntity.ok(String.format("%d parts deleted successfully", deletedCount));
       
    }
}
