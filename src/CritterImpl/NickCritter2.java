package CritterImpl;
import providedCode.Berry;
import providedCode.Coordinate;
import providedCode.Critter;
import providedCode.Move;

public class NickCritter2 implements Critter {
	
	@Override
	public Move sendMove(Object[][] grid) {
		Coordinate me = findSelf(grid);
		Coordinate berry = findBerry(grid);
		
		if (berry.getX() > me.getX()) {
			return Move.RIGHT;
		} else if (berry.getX() < me.getX()){
			return Move.LEFT;
		}
		
		if (berry.getY() > me.getY()) {
			return Move.UP;
		}
		
		return Move.DOWN;
	}
	
	@Override
	public String getSymbol() {
		return "\u270C";
	}
	
	private Coordinate findBerry(Object[][] grid) {
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				if (grid[y][x] instanceof Berry) {
					return new Coordinate(x, y);
				}
			}
		}
		return null;
	}
	
	private Coordinate findSelf(Object[][] grid) {
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				if (grid[y][x] == this) {
					return new Coordinate(x, y);
				}
			}
		}
		return null;
	}

}
