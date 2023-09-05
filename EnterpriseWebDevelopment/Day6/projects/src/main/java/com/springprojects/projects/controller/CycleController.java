package com.springprojects.projects.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springprojects.projects.customAnnotations.Authenticated;
import com.springprojects.projects.entity.Cycle;
import com.springprojects.projects.repository.CycleStockJpaRepository;
import com.springprojects.projects.repository.getCycleData;
import com.springprojects.projects.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;

//import com.prodapt.learningspring.controller.binding.AddPostForm;

@Controller
@RequestMapping("/cycles")
public class CycleController {

	@Autowired
	private getCycleData rentCycle;

	@Autowired
    private AuthenticationService authService;
	
	@Autowired
	private CycleStockJpaRepository cycleStockJpaRepository;
	
	private List<Cycle> availableList;
	private List<Cycle> rentedList;
	private Optional<Cycle> cycle;
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		return "login";
	}
	@PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (authService.authenticate(username, password)) {
            // Set a session ID or any other session attribute
            session.setAttribute("userId", username);
            System.out.println(username);
            return String.format("redirect:/cycles/restock");
        } else {
        	System.out.println(username);
        	return String.format("redirect:/cycles/login");
        }
    }
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return String.format("redirect:/cycles/login");
	}
	
	@GetMapping("/rent")
	public String getCycleAvailable(Model model) {
		availableList = new ArrayList<>();
		System.out.println("In Rent Mappign");
		model.addAttribute("cyclesAvailable");
		rentCycle.findByStatus(false).forEach(Cycle -> availableList.add(Cycle));
		model.addAttribute("userList", availableList);
		return "cyclesAvailable";
	}

	@PostMapping("/rent")
	public String rentACycle(@RequestParam("rentId") int id) {
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
		return "cyclesReturn";
	}
	
	@PostMapping("/return")
	public String returnACycle(@RequestParam("rentId") int id) {
		cycle = rentCycle.findById(id);
		Cycle c = cycle.get();
		c.setStatus(false);
		rentCycle.save(c);
		return String.format("redirect:/cycles/return");
	}
	
	@GetMapping("/list")
	public String getCyclesInStock(Model model) {
		availableList = new ArrayList<>();
		model.addAttribute("cycleList");
		model.addAttribute("userList", cycleStockJpaRepository.findIdsWithNonZeroStock());
		return "cycleList";
	}
	
	@GetMapping("/restock")
	@Authenticated
	public String restockCycles(Model model) {
		availableList = new ArrayList<>();
		model.addAttribute("addCycleStock");
		model.addAttribute("userList", cycleStockJpaRepository.findAll());
		return "addCycleStock";
	}
	
	@PostMapping("/restock")
	public String updateStock(@RequestParam("id") int id,@RequestParam("newStock") int stock) {
		int prevStock = cycleStockJpaRepository.findStockById(id);
		cycleStockJpaRepository.updateStockById(id,prevStock+stock);
		return String.format("redirect:/cycles/restock");
	}
	
	@GetMapping("/borrow")
	public String borrowCyclesList(Model model) {
		model.addAttribute("borrowCycle");
		model.addAttribute("userList", cycleStockJpaRepository.findIdsWithNonZeroStock());
		return "borrowCycle";
	}
	
	@PostMapping("/borrow")
	public String borrowCycles(@RequestParam("id") int id) {
		int prevStock = cycleStockJpaRepository.findStockById(id);
		cycleStockJpaRepository.updateStockById(id,--prevStock);
		return String.format("redirect:/cycles/borrow");
	}
	
//	@GetMapping("/return")
//	public String returnCycles(Model model) {
//		model.addAttribute("returnCycle");
//		model.addAttribute("userList", cycleStockJpaRepository.findAll());
//		return "returnCycle";
//	}
//	
//	@PostMapping("/return")
//	public String returnCycles(@RequestParam("id") int id) {
//		int prevStock = cycleStockJpaRepository.findStockById(id);
//		cycleStockJpaRepository.updateStockById(id,++prevStock);
//		return String.format("redirect:/cycles/return");
//	}
}
