package com.learning.hello;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mangatha.Deck;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import com.learning.hello.controller.HiLoController;
import com.learning.hello.controller.MangathaController;
import com.learning.hello.controller.exception.UnsupportedActionException;

/**
 * Servlet implementation class MangathaServlet
 */

@WebServlet("/mangatha")

public class MangathaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MangathaController operator;
	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;
	private boolean state = false,gameStarted = false;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		operator = new MangathaController(); //write for MaganthaController
		//hlc.reset();
		application = JakartaServletWebApplication.buildApplication(getServletContext());
		final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MangathaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final IWebExchange webExchange = this.application.buildExchange(req, resp);
		final WebContext ctx = new WebContext(webExchange);
		if(gameStarted==true) {
		if(operator.getPosition() == true) {
			ctx.setVariable("In", operator.getCurrentCard());
		}else {
		ctx.setVariable("Out", operator.getCurrentCard());
		}
		}
		if(state==true) {	
		ctx.setVariable("result", operator.getWinner()+" Has Won "+ operator.getBetAmount());
		state=false;
		}
		templateEngine.process("mangatha", ctx, resp.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	    String action = request.getParameter("action");
	    switch(action) {
	    
	    case "add":
	    	operator.addPlayer(request.getParameter("playerName"),Integer.parseInt(request.getParameter("betAmount")),Integer.parseInt(request.getParameter("position"))==0?false:true, operator.selectedCard(Integer.parseInt(request.getParameter("rank")),Integer.parseInt(request.getParameter("suit"))));
	      break;
	    case "start":
	    	operator.startGame();
	    	gameStarted = true;
	      break;
	    case "draw":
	    	operator.getCard();
	    	state = operator.isWinner();
	      break;
	    case "reset":
	    	operator.reset();
	    	gameStarted = false;
	    	 break;
	    default:
	    
	    }
	    
	    doGet(request, response);
	    
	}

}
