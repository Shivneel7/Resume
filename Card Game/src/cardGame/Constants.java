package cardGame;

public interface Constants {

	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	public static final int SLOTS = 7;
	
	public static final int SPACE = 20;
	public static final int STACK_SPACING = 30;

	public static final int CARD_WIDTH = (GAME_WIDTH-((SLOTS + 2) * SPACE))/SLOTS; //88
	public static final int CARD_HEIGHT = (int) (CARD_WIDTH * 1.4); //123

	public static final int WASTEPILE_SPACING = CARD_WIDTH/4 * 3;
	
}
