package mangatha;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private List<Player> players = new ArrayList<Player>();
	private Deck deckofCards;
	private boolean position = false;
	public Player winner;
	private boolean hasWon = false;
	private CompareCards compareCards;
	
	
	public Game(List<Player> players) {
		this.players = players;
		deckofCards = new Deck();
		compareCards = new CompareCards();
	}
	
	public String drawCard() {
		position = !position;
		Card currentDraw;
		currentDraw = deckofCards.removeFromTop();
		isWinner(currentDraw);
		return currentDraw.toString();
	}
	
	public void isWinner(Card currentDraw) {
		for (Player player : players) {
			if(compareCards.compare(currentDraw,player.getSelectedCard())==1 && position == player.getPosition()) {
				winner = player;
				this.hasWon = true;
				break;
			}
		}
	}
	
	public boolean checkState() {
		return hasWon;
	}
	
	public boolean getCardPosition() {
		return position;
	}
}
