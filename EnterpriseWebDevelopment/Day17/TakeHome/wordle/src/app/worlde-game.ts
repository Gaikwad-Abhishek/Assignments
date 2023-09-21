export class WordleGame {
    
    secretWord: string;
    attemptsRemaining: number;
    guessedWord: string = '';
    previousFeedback: string = '';
  
    constructor(secretWord: string, maxAttempts: number = 6) {
      this.secretWord = secretWord.toLowerCase();
      this.attemptsRemaining = maxAttempts;
    }
  
    guessWord(word: string): boolean {
      if (word.length !== 5) {
        return false; // Word should be exactly 5 letters long
      }
  
      word = word.toLowerCase();
      this.guessedWord = '';

      for (let i = 0; i < word.length; i++) {
        if (word[i] === this.secretWord[i]) {
          this.guessedWord += word[i];
          this.previousFeedback += word[i];
        } else if (this.secretWord.includes(word[i])) {
          this.guessedWord += '?';
          this.previousFeedback += '?';
        } else {
          this.guessedWord += '*';
          this.previousFeedback += '*';
        }
      }
  
      this.attemptsRemaining--;
  
      return this.guessedWord === this.secretWord;
    }
  
    getAttemptsRemaining(): number {
      return this.attemptsRemaining;
    }
  
    getGuessedWord(): string {
    //   return this.guessedWord;
      return this.previousFeedback;
    }
  }