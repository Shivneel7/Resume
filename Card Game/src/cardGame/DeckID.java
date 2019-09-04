package cardGame;

public enum DeckID {
	STOCK (false), 
	WASTEPILE (false), 
	FOUNDATION (false), 
	TABLEAU (true), 
	HELD (true);
	
	private final boolean stackable;
	
	DeckID(boolean s){
		stackable = s;
	}
	
	public boolean stackable() {return stackable;}
}