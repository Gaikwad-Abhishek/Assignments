package mangatha;

public class Player {
	
	private String name;
	private Card selectedCard;
	private boolean position;
	private Integer betAmount;
	
	public Player(String name,Integer betAmount, boolean position, Card selectedCard) {
        this.name = name;
        this.betAmount = betAmount;
        this.position = position;
        this.selectedCard = selectedCard;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for position
    public boolean getPosition() {
        return position;
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

    // Getter and Setter for selectedCard
    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }
    
    // Getter and Setter for selectedCard
    public Integer getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }
    
}
