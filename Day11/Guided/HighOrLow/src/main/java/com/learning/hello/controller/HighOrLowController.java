package com.learning.hello.controller;

import java.util.Random;

public class HighOrLowController {
	
	  private int answer;
	  private int userGuess;
	  
	  public void setuserGuess(int userGuess) {
	    this.userGuess = userGuess;
	  }
	  
	  public void reset() {
	    this.answer = new Random().nextInt(0, 500);
	  }
	  
	  public String compareInputToAnswer() {
	        if (userGuess > answer) {
	            return "Guess a lower value";
	        } else if (userGuess < answer) {
	            return "Guess a higher value";
	        } else {
	            return "You Won";
	        }
	    }
}
