package game;

public class Game {
	
	public  Board BOARD;
	public  Controls CONTROLS;
	
	public Game(){
		BOARD = new Board(4);
		CONTROLS = new Controls(BOARD);
	}
	public Integer getValueAtPos(Integer row, Integer col){
		return BOARD.getValueTileAt(row,col);
	}
	
	public void printValue(){
		for (int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				System.out.print(BOARD.getValueTileAt(i,j));
			}
			System.out.println("\n");
		}
	}
	
	public void implementMove(String move){
		CONTROLS.playerMove(move);
		if (BOARD.isGameOver()) {
			BOARD = new Board(4);
			CONTROLS = new Controls(BOARD);
		}
	}
}
