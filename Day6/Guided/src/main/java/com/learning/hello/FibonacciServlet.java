package com.learning.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class FibonacciServlet
 */
public class FibonacciServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public List<Integer> fibonacciseq(Integer limit){
		
		Integer x=1,y=1,z=0;
		List<Integer> Fseq = new ArrayList<>();
		if(limit == 1) {
			Fseq.add(x);
		}else if(limit == 2){
			Fseq.add(y);
			Fseq.add(y);
		}else {
			Fseq.add(x);
			Fseq.add(y);
			limit-=2;
			while (limit >0) {
				z=x+y;
				Fseq.add(z);
				x=y;
				y=z;
				limit--;
			}
		}
		return Fseq;
	}
	@Override
	  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	      
	      PrintWriter out = resp.getWriter();
	      
	      if (req.getParameter("n") == "") {
	          out.println("This is not the way to access this resource. Please give a value for the parameter 'n' in your query ");
	          return;
	        } else {
	          int Seqlimit = Integer.valueOf(req.getParameter("n"));
	          out.println(fibonacciseq(Seqlimit));
	        }
//	      parameterMap.clear();
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	  }
	
	  @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    PrintWriter out = response.getWriter();
	    if (request.getParameter("formLimit") == "") {
	      out.println("This is not the way to access this resource. Please give a value for the parameter 'n' in your query ");
	      return;
	    } else {
	      int limit = Integer.valueOf(request.getParameter("formLimit"));
//	      out.println(String.format("<p>%s</p>", fibonaccis(limit)));
	      out.println(fibonacciseq(limit));
	    }
	  }


}