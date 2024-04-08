package com.Netwin.SpareParts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Netwin.SpareParts.model.Parts;

public interface PartsRepository extends  JpaRepository<Parts, Integer> {

	List<Parts> findByPartTitleContainingIgnoreCase(String title);

}