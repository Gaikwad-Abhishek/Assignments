package game;


public class Controls {
	
	Board BOARD;
	Controls(Board Board){
		this.BOARD = Board;
	}
	
	public void playerMove(String move){
		
		switch (move) {
			case "ArrowUp":
				BOARD.moveUp();
				System.out.println("In UP");
				break;
			case "ArrowDown":
				BOARD.moveDown();
				System.out.println("In DOWN");
				break;
			case "ArrowLeft":
				BOARD.moveLeft();
				System.out.println("In LEFT");
				break;
			case "ArrowRight":
				BOARD.moveRight();
				System.out.println("In RIGHT");
				break;
			default:
				break;
		}
	}
		

//		Game.BOARD.isGameOver();

		
}

