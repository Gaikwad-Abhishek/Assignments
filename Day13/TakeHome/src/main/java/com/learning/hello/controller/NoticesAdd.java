package com.learning.hello.controller;

import java.io.IOException;
import java.io.Writer;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import com.learning.hello.controller.exception.UnsupportedActionException;

//import com.learning.hello.model.mankatha.Game;
//import com.learning.hello.model.mankatha.Orientation;
//import com.learning.hello.model.mankatha.Player;

//import cards.Card;
import jakarta.servlet.http.HttpServletResponse;

public class NoticesAdd implements IController {

  @Override
  public void processGet(IWebExchange webExchange, TemplateEngine templateEngine, HttpServletResponse resp)
      throws UnsupportedActionException, IOException {
    WebContext ctx = new WebContext(webExchange);
//    ctx.setVariable("players", Game.getInstance().getPlayers());
    Writer out = resp.getWriter();
    templateEngine.process("notices/noticeadd", ctx, out);
  }

  @Override
  public void processPost(IWebExchange webExchange, TemplateEngine templateEngine, HttpServletResponse resp)
      throws UnsupportedActionException, IOException {
//    Game game = Game.getInstance();
    var req = webExchange.getRequest();
//    Card card = new Card(req.getParameterValue("card"));
//    int bet = Integer.valueOf(req.getParameterValue("bet"));
//    Orientation orientation = Orientation.of(req.getParameterValue("orientation"));
//    Player player = new Player(card, bet, orientation);
//    game.addPlayer(player);
    
    String title = req.getParameterValue("title");
    String content = req.getParameterValue("content");
    String contact = req.getParameterValue("contact");
    
    resp.sendRedirect(req.getRequestPath());
  }

}
