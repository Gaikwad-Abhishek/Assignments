package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

	private int size; 						
	private int score; 						
	private int emptyTiles;					
	private int initTiles = 2; 				
	private boolean gameover = false; 		
	private String wonOrLost;				
	private boolean genNewTile = false;		
	private List<List<Tile>> tiles;			

	public Board(int size) {
		super();
		this.size = size;
		this.emptyTiles = this.size * this.size;
		this.tiles = new ArrayList<>();

		start();
	}

	private void initialize() {
		for (int row = 0; row < this.size; row++) {
			tiles.add(new ArrayList<Tile>());
			for (int col = 0; col < this.size; col++) {
				tiles.get(row).add(new Tile());
			}
		}
	}
	
	private void start() {
	
		initialize();
		genInitTiles();
		//show();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<List<Tile>> getTiles() {
		return tiles;
	}

	public void setTiles(List<List<Tile>> tiles) {
		this.tiles = tiles;
	}

	public Tile getTileAt(int row, int col) {
		return tiles.get(row).get(col);
	}
	
	public Integer getValueTileAt(int row, int col) {
		return tiles.get(row).get(col).getValue();
	}

	public void setTileAt(int row, int col, Tile t) {
		tiles.get(row).set(col, t);
	}

	public void remTileAt(int row, int col) {
		tiles.get(row).remove(col);
	}
	
	public int getScore() {
		return score;
	}


	private List<Tile> mergeTiles(List<Tile> sequence) {
		
		List<Integer> fibonacciValues = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584);
		
		for (int l = 0; l < sequence.size() - 1; l++) {
			if(fibonacciValues.indexOf(sequence.get(l).getValue()) != 0){
			if (fibonacciValues.get(fibonacciValues.indexOf(sequence.get(l).getValue()) - 1) == sequence.get(l + 1).getValue()) {
				System.out.println("In First If");
				int value;
				if ((value = sequence.get(l).merging(fibonacciValues.get(fibonacciValues.indexOf(sequence.get(l).getValue()) + 1))) == 8) {
					gameover = true;
				}
				score += value;
				sequence.remove(l + 1);
				genNewTile = true; 
				emptyTiles++;
			}else if(fibonacciValues.get(fibonacciValues.indexOf(sequence.get(l).getValue()) + 1) == sequence.get(l + 1).getValue()) {
				System.out.println("In Second If");
				int value;
				if ((value = sequence.get(l).merging(fibonacciValues.get(fibonacciValues.indexOf(sequence.get(l).getValue()) + 2))) == 8) {
					gameover = true;
				}
				score += value;
				sequence.remove(l + 1);
				genNewTile = true; 
				emptyTiles++;
			}
		  }else{
				if(fibonacciValues.get(fibonacciValues.indexOf(sequence.get(l).getValue()) + 1) == sequence.get(l + 1).getValue()) {
				System.out.println("In Second If");
				int value;
				if ((value = sequence.get(l).merging(fibonacciValues.get(fibonacciValues.indexOf(sequence.get(l).getValue()) + 2))) == 8) {
					gameover = true;
				}
				score += value;
				sequence.remove(l + 1);
				genNewTile = true; 
				emptyTiles++;
			}

		  }
		}
		return sequence;
	}

	
	private List<Tile> addEmptyTilesFirst(List<Tile> merged) {
		for (int k = merged.size(); k < size; k++) {
			merged.add(0, new Tile());
		}
		return merged;
	}

	
	private List<Tile> addEmptyTilesLast(List<Tile> merged) { 
		for (int k = merged.size(); k < size; k++) {
			merged.add(k, new Tile());
		}
		return merged;
	}

	private List<Tile> removeEmptyTilesRows(int row) {

		List<Tile> moved = new ArrayList<>();

		for (int col = 0; col < size; col++) {
			if (!getTileAt(row, col).isEmpty()) { 
				moved.add(getTileAt(row, col));
			}
		}

		return moved;
	}

	private List<Tile> removeEmptyTilesCols(int row) {

		List<Tile> moved = new ArrayList<>();

		for (int col = 0; col < size; col++) {
			if (!getTileAt(col, row).isEmpty()) { 
				moved.add(getTileAt(col, row));
			}
		}

		return moved;
	}

	private List<Tile> setRowToBoard(List<Tile> moved, int row) {
		for (int col = 0; col < tiles.size(); col++) {
			if (moved.get(col).hasMoved(row, col)) {
				genNewTile = true;
			}
			setTileAt(row, col, moved.get(col));
		}

		return moved;
	}

	private List<Tile> setColToBoard(List<Tile> moved, int row) {
		for (int col = 0; col < tiles.size(); col++) {
			if (moved.get(col).hasMoved(col, row)) {
				genNewTile = true;
			}			
			setTileAt(col, row, moved.get(col));
		}

		return moved;
	}

	public void moveUp() {

		List<Tile> moved;

		for (int row = 0; row < size; row++) {

			moved = removeEmptyTilesCols(row);
			moved = mergeTiles(moved);
			moved = addEmptyTilesLast(moved);
			moved = setColToBoard(moved, row);

		}

	}

	public void moveDown() {

		List<Tile> moved;

		for (int row = 0; row < size; row++) {

			moved = removeEmptyTilesCols(row);
			moved = mergeTiles(moved);
			moved = addEmptyTilesFirst(moved);
			moved = setColToBoard(moved, row);

		}
		
	}

	public void moveLeft() {

		List<Tile> moved;

		for (int row = 0; row < size; row++) {

			moved = removeEmptyTilesRows(row);
			moved = mergeTiles(moved);
			moved = addEmptyTilesLast(moved);
			moved = setRowToBoard(moved, row);

		}
		
	}

	public void moveRight() {
		
		List<Tile> moved;
		
		for (int row = 0; row < size; row++) {

			moved = removeEmptyTilesRows(row);
			moved = mergeTiles(moved);
			moved = addEmptyTilesFirst(moved);
			moved = setRowToBoard(moved, row);

		}
		
	}
	
	public boolean isGameOver() {
		
		if (gameover) {
			setWonOrLost("WON");
			return true;
		} else {
			if (isFull()) {
				if (!isMovePossible()) {
					
					setWonOrLost("LOST");
				}
				
			} else {
				newRandomTile(); 
			}
		}
		return false;
	}
	
	private boolean isFull() {
		return emptyTiles == 0;
	}
	
	private boolean isMovePossible() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size - 1; col++) {
				if (getTileAt(row, col).getValue() == getTileAt(row, col + 1).getValue()) {
					return true;
				}
			}
		}
		
		for (int row = 0; row < size - 1; row++) {
			for (int col = 0; col < size; col++) {
				if (getTileAt(col, row).getValue() == getTileAt(col, row + 1).getValue()) {
					return true;
				}
			}
		}
		return false;
	}

	private void genInitTiles() {
		for (int i = 0; i < initTiles; i++) {
			genNewTile = true;
			newRandomTile();
		}
	}

	private void newRandomTile() {
		if (genNewTile) {
			int row;
			int col;
			int value = Math.random() < 0.9 ? 1 : 3;
			do {
				row = (int) (Math.random () * 4);
				col = (int) (Math.random () * 4);
			} while (getTileAt(row, col).getValue() != 0);
			setTileAt(row, col, new Tile(value, row, col));
			emptyTiles--;
			genNewTile = false;
		}
	}

	protected void show() {
		for (int i = 0; i < 2; ++i) System.out.println();
		System.out.println("SCORE: " + score);
		for (int i = 0; i < tiles.size(); i++) {
			for (int j = 0; j < tiles.get(i).size(); j++) {
				System.out.format("%-5d", getTileAt(i, j).getValue());
			}
			System.out.println();
		}
	}

	public String getWonOrLost() {
		return wonOrLost;
	}

	public void setWonOrLost(String wonOrLost) {
		this.wonOrLost = wonOrLost;
	}

}