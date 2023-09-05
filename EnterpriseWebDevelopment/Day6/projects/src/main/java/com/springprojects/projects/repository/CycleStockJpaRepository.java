package com.springprojects.projects.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springprojects.projects.entity.CycleStock;

import jakarta.transaction.Transactional;

@Repository
public interface CycleStockJpaRepository extends JpaRepository<CycleStock, Integer>{
	
	@Query(value = "SELECT * FROM cycle_stock WHERE stock <> 0", nativeQuery = true)
    List<CycleStock> findIdsWithNonZeroStock();

	@Query(value = "SELECT c.stock FROM cycle_stock c WHERE c.id = ?1", nativeQuery = true)
	int findStockById(int id);
	
	@Modifying
	@Transactional	
	@Query(value = "UPDATE cycle_stock c SET c.stock = ?2 WHERE c.id = ?1", nativeQuery = true)
	void updateStockById(int id,int newStock);
	
}