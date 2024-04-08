package com.Netwin.SpareParts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Netwin.SpareParts.model.Parts;
import com.Netwin.SpareParts.repository.PartsRepository;


@Service
public class PartsService {

    @Autowired
    private PartsRepository partsRepository;

    public void addPart(Parts part) 
    {
        partsRepository.save(part);
    }

    public void editPart(int partId, Parts part) {
        Parts existingPart = partsRepository.findById(partId).orElse(null);
        if (existingPart != null) {
            existingPart.setPartId(partId); 
            existingPart.setModel(part.getModel());
            existingPart.setCategory(part.getCategory());
            existingPart.setPartTitle(part.getPartTitle());
            existingPart.setPartPrice(part.getPartPrice());
            
            partsRepository.save(existingPart);
        }
    }


    public void deletePart(int partId) {
        partsRepository.deleteById(partId);
    }

    public List<Parts> getAllParts() {
        return partsRepository.findAll();
    }
    
    public Parts addPart(int partId) {
		
		return partsRepository.findById(partId).get();
	}
    
    public List<Parts> searchPartsByTitle(String title) {
      return partsRepository.findByPartTitleContainingIgnoreCase(title);
     
   }

    public Parts getPartById(int partId) {
       return partsRepository.findById(partId).get();
        
    }

	 
}
