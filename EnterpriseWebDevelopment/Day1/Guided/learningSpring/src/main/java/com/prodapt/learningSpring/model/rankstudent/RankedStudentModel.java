package com.prodapt.learningSpring.model.rankstudent;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rankingstudents.rankingstudents.RankStudents;
import com.rankingstudents.rankingstudents.Student;

@Component
public class RankedStudentModel {
	
	RankStudents rankStudent = new RankStudents();
	
	public void newStudent(int id,String name, Integer score) {
		 rankStudent.newStudent(id,name,score);
	}
	
	public List<Student> retriveStudentList(){
		return rankStudent.retriveStudentList();
	}
	
}
