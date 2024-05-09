package com.Netwin.SpareParts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Netwin.SpareParts.model.Parts;

import jakarta.transaction.Transactional;

public interface PartsRepository extends JpaRepository<Parts, Integer> {

	List<Parts> findByPartTitleContainingIgnoreCase(String title);

	@Query(value = "SELECT * FROM Parts WHERE category_id = ?1", nativeQuery = true)
	List<Parts> findByCategoryID(int categoryId);

	@Query(value = "SELECT * FROM Parts WHERE model_id = ?1", nativeQuery = true)
	List<Parts> findPartsByModelNative(int modelId);
//--------------------------------------------------------------------------------
	// Native query to update part price by part ID
    @Transactional
    @Modifying
    @Query(value = "UPDATE Parts SET part_price = ?2 WHERE part_id = ?1", nativeQuery = true)
    int updatePartPriceNative(int partId, double partPrice);

    
    @Query(value = "SELECT * FROM Parts WHERE part_price > ?1", nativeQuery = true)
	List<Parts> findPartsByPriceGreaterThan(double price);

    @Query(value = "SELECT * FROM Parts WHERE part_price < ?1", nativeQuery = true)
	List<Parts> findPartsByPriceLessThan(double price);
//---------------------------------------------------------------------------------------
    // Native query to find parts with a price within a specified range
    @Query(value = "SELECT * FROM Parts WHERE part_price BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Parts> findPartsByPriceRangeNative(double minPrice, double maxPrice);

    // Native query to find parts with a specific title containing a keyword
    @Query(value = "SELECT * FROM Parts WHERE part_title LIKE %?1%", nativeQuery = true)
    List<Parts> findPartsByTitleContainingNative(String keyword);

    // Native query to delete parts by category ID
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Parts WHERE category_id = ?1", nativeQuery = true)
    int deletePartsByCategoryNative(int categoryId);
}