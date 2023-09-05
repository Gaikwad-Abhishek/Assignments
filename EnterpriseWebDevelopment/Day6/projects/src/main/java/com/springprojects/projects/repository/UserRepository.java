package com.springprojects.projects.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springprojects.projects.entity.CycleStock;
import com.springprojects.projects.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	@Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    Optional<User> findByUsername(String username);

	
}
