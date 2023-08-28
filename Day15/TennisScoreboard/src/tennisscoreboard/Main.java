package tennisscoreboard;

import java.util.Random;

public class Main {

    // working main start
    public static void main(String[] args) {

    Player nadal = new Player();
    Player fedrer = new Player();
    Scoreboard match = new Scoreboard(nadal,fedrer);
    Random random = new Random();

    while (match.getWinner() == -1) {
    // int gameWin = random.nextInt(2);
    int gameWin = 0;
    match.updateGamePoints(gameWin);
    gameWin = 0;
    match.updateGamePoints(gameWin);
    gameWin = 0;
    match.updateGamePoints(gameWin);
    gameWin = 1;
    match.updateGamePoints(gameWin);
    gameWin = 1;
    match.updateGamePoints(gameWin);
    gameWin = 1;
    match.updateGamePoints(gameWin);
    gameWin = 0;
    match.updateGamePoints(gameWin);
    gameWin = 0;
    match.updateGamePoints(gameWin);

    // gameWin = 0;
    // match.updateGamePoints(gameWin);

    match.printScore();
    }

    int matchWinner = match.getWinner();
    System.out.println("Winner: " + (matchWinner == 0 ? "Federer" : "Nadal"));
    match.printSets();

    }
    // working main end

}
