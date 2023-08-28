package tennisscoreboard;

public class Scoreboard {

    // private int[] setsWon; // Keeps track of sets won by each player
    // private int[] gamesWon; // Keeps track of games won in the current set by each player
    // private int[] pointsWon; // Keeps track of points won in the current game by each player
    private Player[] players;
    private int servingPlayer; // 0 for Fed, 1 for Nadal
    private int currentSet; // Index of the current set (0, 1, 2)

    public Scoreboard(Player player1, Player player2 ) {
        // setsWon = new int[2];
        // gamesWon = new int[2];
        // pointsWon = new int[2];
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        servingPlayer = 0;
        currentSet = 1;
    }

    public int getWinner() {
        int maxSets = 3; // Number of sets required to win the match in a 3-set game
        int setsNeededToWin = 2; // Number of sets needed to win the match

        if (currentSet >= maxSets) {
            if (players[0].getSetsWon() >= setsNeededToWin) {
                return 0; // Federer wins the match
            } else if (players[1].getSetsWon() >= setsNeededToWin) {
                return 1; // Nadal wins the match
            }
        }

        return -1; // Match is not yet decided
    }

    public void updateGamePoints(int winner) {
        
        players[winner].setPointsWon();;
        
        // Update game and set scores based on the rules
        
        // Check for deuce and advantage
        if (players[0].getPointsWon() >= 3 && players[1].getPointsWon() >= 3) {
            if (players[0].getPointsWon() == players[1].getPointsWon()) {
                // Deuce
                players[0].setPointsWon(3);
                players[1].setPointsWon(3);
            } else if (Math.abs(players[0].getPointsWon() - players[1].getPointsWon()) == 1) {
                // Advantage
                return;
            }
        }
        
        // Check for game and set wins
        if (players[0].getPointsWon() >= 4 && players[0].getPointsWon() - players[1].getPointsWon() >= 2) {
            players[0].setGamesWon();
            resetGame();
        } else if (players[1].getPointsWon() >= 4 && players[1].getPointsWon() - players[0].getPointsWon() >= 2) {
            players[1].setGamesWon();
            resetGame();
        }
        
        // Check for set wins
        if (players[0].getGamesWon() >= 6 && players[0].getGamesWon() - players[1].getGamesWon() >= 2) {
            players[0].setSetsWon();;
            resetSet();
        } else if (players[1].getGamesWon() >= 6 && players[1].getGamesWon() - players[0].getGamesWon() >= 2) {
            players[1].setSetsWon();
            resetSet();
        }
        
        // Handle tiebreaker
        if (players[0].getGamesWon() == 6 && players[1].getGamesWon() == 6) {
            if (players[0].getPointsWon() >= 7 && players[0].getPointsWon() - players[1].getPointsWon() >= 2) {
                players[0].setSetsWon();;
                resetSet();
            } else if (players[1].getPointsWon() >= 7 && players[1].getPointsWon() - players[0].getPointsWon() >= 2) {
                players[1].setSetsWon();;
                resetSet();
            }
        }
    }

    private void resetGame() {
        players[0].setPointsWon(0); 
        players[1].setPointsWon(0); 
        servingPlayer = 1 - servingPlayer;
    }

    private void resetSet() {
        players[0].setGamesWon(0);
        players[1].setGamesWon(0);
        players[0].setPointsWon(0); 
        players[1].setPointsWon(0); 
        servingPlayer = 1 - servingPlayer;
        currentSet++;
    }

    //remove this 
    public void printScore() {
        System.out.println("Current Set: " + currentSet);
        System.out.println("Player 1 (Federer): " + players[0].getGamesWon() + " games");
        System.out.println("Player 2 (Nadal): " + players[1].getGamesWon() + " games");
        System.out.println("Serving: " + (servingPlayer == 0 ? "Federer" : "Nadal"));
        System.out.println("Points: " + pointsToString(players[0].getPointsWon()) + " - " + pointsToString(players[1].getPointsWon()));
        System.out.println();
    }


    //remove this
    public void printSets() {
        System.out.println("Sets Won - Federer: " + players[0].getSetsWon() + ", Nadal: " + players[1].getSetsWon());
    }

    private String pointsToString(int points) {
        String[] pointNames = {"0", "15", "30", "40", "adv"};
        return pointNames[points];
    }

}

