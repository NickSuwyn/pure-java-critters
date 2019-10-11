package providedCode;

public interface Critter extends Solid {
	/**
	 * 
	 * @param grid Object[y][x]
	 * @return Direction to move
	 */
	public Move sendMove(Object[][] grid);
	
	public String getSymbol();

}
