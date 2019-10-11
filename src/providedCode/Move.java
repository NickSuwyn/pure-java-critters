package providedCode;

public enum Move {
	
	UP 		("up"), 
	DOWN	("down"), 
	LEFT	("left"), 
	RIGHT	("right");
	
	private final String value;
	
	Move(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
