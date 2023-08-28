package tennisscoreboard;

public class Player {
    
    private int setsWon; 
    private int gamesWon; 
    private int pointsWon; 

    public Player(){
      this.setsWon = 0; 
      this.gamesWon = 0; 
      this.pointsWon = 0; 
    }

    public int getSetsWon() {
        return setsWon;
    }

    public void setSetsWon() {
        this.setsWon++;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon() {
        this.gamesWon++;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getPointsWon() {
        return pointsWon;
    }

    public void setPointsWon() {
        this.pointsWon++;
    }

    public void setPointsWon(int points) {
        this.pointsWon = points;
    }
    
    public String pointsToString(int points) {
        String[] pointNames = {"0", "15", "30", "40", "adv"};
        return pointNames[points];
    }
}
