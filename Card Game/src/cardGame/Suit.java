package cardGame;

import java.awt.Color;

public enum Suit {
	SPADE (Color.black),
	HEART (Color.red),
	DIAMOND (Color.red),
	CLUB (Color.black);
	
	private Suit(Color c) {
		color = c;
	}
	
	private Color color;
	
	public Color color() {
		return color;
	}
}
