package com.learning.hello;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import com.learning.hello.controller.HiLoController;
import com.learning.hello.controller.HighOrLowController;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/highorlow")

public class HighOrLow extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private HighOrLowController hlc;
	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    hlc = new HighOrLowController();
	    hlc.reset();
	    application = JakartaServletWebApplication.buildApplication(getServletContext());
	    final WebApplicationTemplateResolver templateResolver = 
	        new WebApplicationTemplateResolver(application);
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    templateResolver.setPrefix("/WEB-INF/templates/");
	    templateResolver.setSuffix(".html");
	    templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	 }
	  
	  @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    hlc.setuserGuess(Integer.parseInt(req.getParameter("inputGuess")));
	    var out = resp.getWriter();
	    final IWebExchange webExchange = 
	        this.application.buildExchange(req, resp);
	    final WebContext ctx = new WebContext(webExchange);
	    ctx.setVariable("feedback", hlc.compareInputToAnswer());
	    templateEngine.process("highorlow", ctx, out);
	    if (hlc.compareInputToAnswer().equals("You Won"))
	      hlc.reset();
	  }
	  
	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    final IWebExchange webExchange = this.application.buildExchange(req, resp);
	    final WebContext ctx = new WebContext(webExchange);
	    templateEngine.process("highorlow", ctx, resp.getWriter());
	  }

}
