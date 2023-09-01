package com.springprojects.projects.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springprojects.projects.entity.Cycle;
import com.springprojects.projects.repository.getCycleData;


//import com.prodapt.learningspring.controller.binding.AddPostForm;

@Controller
@RequestMapping("/cycles")
public class CycleController {
	
	@Autowired
	private getCycleData rentCycle;
	
	private List<Cycle> availableList;
	private List<Cycle> rentedList;
	private Optional<Cycle> cycle;
	
	@GetMapping("/rent")
	  public String getCycleAvailable(Model model) {
		availableList = new ArrayList<>();
		System.out.println("In Rent Mappign");
	    model.addAttribute("cyclesAvailable");
	    rentCycle.findByStatus(false).forEach(Cycle -> availableList.add(Cycle));
	    model.addAttribute("userList", availableList);
	    return "cyclesAvailable";
	  }
	
	@PostMapping("/rent/rented") 
		public String rentACycle(@PathVariable int id) {
		System.out.println(id);
		cycle = rentCycle.findById(Integer.valueOf(id));
		Cycle c = cycle.get();
		c.setStatus(true);
		rentCycle.save(c);
		return String.format("redirect:/rent");
	}
	
	@GetMapping("/rent/{id}")
	  public String rentCycle(@PathVariable int id) {
		
		cycle = rentCycle.findById(id);
		Cycle c = cycle.get();
		c.setStatus(true);
		rentCycle.save(c);
		return String.format("redirect:/cycles/rent");
	}
	
	@GetMapping("/return")
	  public String getRentedCycle(Model model) {
		rentedList = new ArrayList<>();
	    model.addAttribute("cyclesAvailable");
	    rentCycle.findByStatus(true).forEach(Cycle -> rentedList.add(Cycle));
	    model.addAttribute("userList", rentedList);
	    return "cyclesAvailable";
	  }
	
	@PostMapping("/return/{id}")
	 public String returnCycle() {
//		rentCycle.updateStatusById(10,false);
		return String.format("redirect:/cycles/rent");
	}
}
