package com.learning.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class passwordValidate
 */
public class PasswordValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    @Override
	  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	      
	      PrintWriter out = resp.getWriter();
	      out.println("This is not the way to access this resource. Please give a value for the parameter 'n' in your query ");
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	  }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	List<String> bannedPasswords = new ArrayList<>();
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		try (BufferedReader reader = new BufferedReader(new FileReader("/home/abhisheksg/eclipse-workspace/learning-web/src/bannedpasswords.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	bannedPasswords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		if(bannedPasswords.contains(password)) {
			out.println("Password is banned");
			
		}else {
			out.println(bannedPasswords);
			out.println("Password Accepted");
		}
		
		response.setContentType("text/html;charset=UTF-8");
	}

}
