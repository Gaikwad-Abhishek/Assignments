package com.springprojects.projects.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import com.prodapt.learningspring.entity.User;
import com.springprojects.projects.entity.Cycle;

@Repository
public interface getCycleData extends CrudRepository<Cycle, Integer>{
//  Optional<Cycle> findByName(String name); 
//  Integer countByName(String name);
//  @Query(value = "select * from cycle where status = ?1", nativeQuery = true)
//  Cycle findAllId(int condition);
  List<Cycle> findByStatus(boolean status);
//  Cycle findById(int id);
}