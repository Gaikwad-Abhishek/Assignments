import { Component } from '@angular/core';
import { WordleGame } from '../worlde-game';

@Component({
  selector: 'app-wordle',
  templateUrl: './wordle.component.html',
  styleUrls: ['./wordle.component.css']
})

export class WordleComponent {
   
  // wordleGame: WordleGame;
  // isGameWon: boolean = false;
  // isGameOver: boolean = false;
  // guessedWord: string = '';

  // constructor() {
  //   this.wordleGame = new WordleGame('apple'); // Set your secret word here
  // }

  // guessWord(word: string) {
  //   if (!this.isGameOver) {
  //     const isCorrect = this.wordleGame.guessWord(word);
  //     this.isGameWon = isCorrect;
  //     if (isCorrect || this.wordleGame.getAttemptsRemaining() === 0) {
  //       this.isGameOver = true;
  //     }
  //   }
  // }

  // restartGame() {
  //   this.wordleGame = new WordleGame('apple'); // Set your secret word here
  //   this.isGameOver = false;
  //   this.isGameWon = false;
  // }
  wordleGame: WordleGame;
  isGameWon: boolean = false;
  isGameOver: boolean = false;
  guessedWord: string = '';

  constructor() {
    this.wordleGame = new WordleGame('apple'); // Set your secret word here
  }

  guessWord(word: string) {
    if (!this.isGameOver) {
      const isCorrect = this.wordleGame.guessWord(word);
      console.log("Output"+isCorrect);
      if (isCorrect) { 
        this.isGameWon = true;
        this.isGameOver = true; // Correct word guessed, end the game
      } else if (this.wordleGame.getAttemptsRemaining() === 0) {
        this.isGameOver = true; // No attempts remaining, end the game
      }
    }
  }

  restartGame() {
    this.wordleGame = new WordleGame('apple'); // Set your secret word here
    this.isGameOver = false;
    this.isGameWon = false;
    this.guessedWord = ''; // Reset the guessed word input
  }
}
