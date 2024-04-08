package com.Netwin.SpareParts.controller;

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
}
