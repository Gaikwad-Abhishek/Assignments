package com.prodapt.learningSpring.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prodapt.learningSpring.model.rankstudent.RankedStudentModel;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/rankstudent")
public class RankStudentController {

	@Autowired
	  private RankedStudentModel studentModel;
	  
	  @GetMapping
	  public String guessAndShow(Model model) {
		  model.addAttribute("matchesList", studentModel.retriveStudentList());
		  return "rankedstudents";
	  }
	  
	  @PostMapping
	  public void processFeedback(String id, String name, String score, HttpServletResponse resp) throws IOException {
		studentModel.newStudent(Integer.valueOf(id),name,Integer.valueOf(score));
	    resp.sendRedirect("/rankstudent");
	  }
	  
	  
}
