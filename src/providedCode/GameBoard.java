package providedCode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import CritterImpl.NickCritter;
import CritterImpl.NickCritter2;

public class GameBoard {
	
	private Object[][] grid = new Object[10][10];
	private Map<Object, Coordinate> gridItems = new HashMap<Object, Coordinate>();
	private Random random = new Random();
	private Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		Critter c1 = new NickCritter();
		Critter c2 = new NickCritter2();
		GameBoard gameBoard = new GameBoard();
		gameBoard.run(c1, c2);
	}
	
	private void run(Critter c1, Critter c2) {
		
		Berry berry = new Berry();
		addObjects(berry, c1, c2, new Block(), new Block(), new Block(), new Block(), new Block(), new Block());
		String winner = "";
		
		drawGrid();
		
		while (winner.equals("")) {
			System.out.println("Press enter for next round");
			scanner.nextLine();
			try {
				moveObject(c1.sendMove(grid), c1);
				if (isCollision(c1, berry)) {
					winner = c1.getSymbol();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				winner = c2.getSymbol();
				break;
			}
			
			try {
				moveObject(c2.sendMove(grid), c2);
				if (isCollision(c2, berry)) {
					winner = c2.getSymbol();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				winner = c1.getSymbol();
				break;
			}
			
			drawGrid();
		}
		
		System.out.println("The winner is: " + winner);
		
	}
	
	private void drawGrid() {
		for (Object[] row : grid) {
			for (Object cell : row) {
				String character = "\u25A2";
				if (cell instanceof Critter) {
					character = ((Critter) cell).getSymbol();
				} else if (cell instanceof Berry) {
					character = "\uD83C\uDF52";
				} else if (cell instanceof Block) {
					character = "\u25A0";
				}
				System.out.print(character);
			}
			System.out.println();
		}
		System.out.println("\n\n");
	}
	
	private void moveObject(Move move, Object obj) {
		Coordinate c = gridItems.get(obj);	
		if (move == Move.UP && isMoveOpen(c.getX(), c.getY() + 1)) {
			grid[c.getY() + 1][c.getX()] = obj;
			grid[c.getY()][c.getX()] = null;
			c.setY(c.getY() + 1);
		} else if (move == Move.DOWN && isMoveOpen(c.getX(), c.getY() - 1)) {
			grid[c.getY() - 1][c.getX()] = obj;
			grid[c.getY()][c.getX()] = null;
			c.setY(c.getY() - 1);
		} else if (move == Move.LEFT && isMoveOpen(c.getX() - 1, c.getY())) {
			grid[c.getY()][c.getX() - 1] = obj;
			grid[c.getY()][c.getX()] = null;
			c.setX(c.getX() - 1);
		} else if (move == Move.RIGHT && isMoveOpen(c.getX() + 1, c.getY())) {
			grid[c.getY()][c.getX() + 1] = obj;
			grid[c.getY()][c.getX()] = null;
			c.setX(c.getX() + 1);
		} 
	}
	
	private boolean isMoveOpen(int x, int y) {
		return !(grid[y][x] instanceof Solid);
	}
	
	private boolean isCollision(Object o1, Object o2) {
		Coordinate c1 = gridItems.get(o1);
		Coordinate c2 = gridItems.get(o2);
		return c1.getX() == c2.getX() && c1.getY() == c2.getY();
	}
	
	private void addObjects(Object... objects) {
		for (Object obj : objects) {
			int x = random.nextInt(9 - 0 + 1) + 0;
			int y = random.nextInt(9 - 0 + 1) + 0;
			if (grid[y][x] == null) {
				grid[y][x] = obj;
				gridItems.put(obj, new Coordinate(x, y));
				System.out.println("Added Object: " + obj + " at x:" + x + " y:" + y);
			} else {
				addObjects(obj);
			}
		}
	}

}
