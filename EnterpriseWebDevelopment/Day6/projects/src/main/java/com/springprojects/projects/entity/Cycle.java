package com.springprojects.projects.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Cycle {
	
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  public int id;
  
  public boolean status;
  
}
