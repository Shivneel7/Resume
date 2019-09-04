package cardGame;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


public class Handler implements Constants{
	private Random r;
	private SpriteSheet ss;
	/**
	 * 14 decks
	 * @indices
	 * deck = {0 stock, 1 wastepile, 2-5 foundations, 6-12 tableau, 13 held}
	 */
	private Deck[] decks = new Deck[14];
	
	public Handler() {
		r = new Random();
		ss = new SpriteSheet();
		makeBoard();
		Card.held = false;
	}
	
	public void makeBoard() {
		ArrayList<Card> freshDeck = getFreshDeck();
		
		//tableau, 6-12
		for(int i = 0; i < 7; i ++) {
			decks[i + 6] = new Deck((SPACE * (i+2)) + CARD_WIDTH * i , SPACE * 2 + CARD_HEIGHT, DeckID.TABLEAU);
			for(int j = 0; j <= i; j++) {
				Card temp = freshDeck.get(r.nextInt(freshDeck.size()));
				decks[i + 6].addCard(temp);
				temp.setX((SPACE * (i+2)) + CARD_WIDTH * i);
				temp.setY((SPACE * 2 + CARD_HEIGHT));
				freshDeck.remove(temp);
			}
		}
		
		//stock
		decks[0] = new Deck(SPACE,SPACE, DeckID.STOCK);
		for(int i = 0; i < 24; i++) {
			Card temp = freshDeck.get(r.nextInt(freshDeck.size()));
			decks[0].addCard(temp);
			temp.setX((SPACE));
			temp.setY((SPACE));
			freshDeck.remove(temp);
		}
		
		//wastePile
		decks[1] = new Deck(SPACE * 2 + CARD_WIDTH, SPACE, DeckID.WASTEPILE);
		
		//foundations
		for(int i = 0; i < 4; i ++) {
			decks[i+2] = new Deck((SPACE * (i + 5)) + CARD_WIDTH * (i + 3), SPACE, DeckID.FOUNDATION);
		}
		
		//held
		decks[13] = new Deck(GAME_WIDTH, GAME_HEIGHT, DeckID.HELD);
	}
	
	public void resetBoard() {
		for(int i = 0; i < decks.length; i ++) {
			decks[i] = null;
		}
		makeBoard();
	}
	
	public ArrayList<Card> getFreshDeck(){
		ArrayList<Card> allCards = new ArrayList<>();
		
		for(int i = 1; i <= 13; i ++) {
			allCards.add(new Card(0, 0, Suit.SPADE, i, ss.grabCardGraphic(i, 2, 60, 80)));
		}
		for(int i = 1; i <= 13; i ++) {
			allCards.add(new Card(0, 0, Suit.CLUB, i, ss.grabCardGraphic(i, 1, 60, 80)));
		}
		for(int i = 1; i <= 13; i ++) {
			allCards.add(new Card(0, 0, Suit.HEART, i, ss.grabCardGraphic(i, 3, 60, 80)));
		}
		for(int i = 1; i <= 13; i ++) {
			allCards.add(new Card(0, 0, Suit.DIAMOND, i, ss.grabCardGraphic(i, 4, 60, 80)));
		}
		
		return allCards;
	}
	
	public void tick() {
		for(Deck d: decks) {
			if(d != null) d.tick();
		}
	}
	
	public void render(Graphics g) {
		for(Deck d: decks) {
			if(d != null) d.render(g);
		}
	}
	
	public Deck[] getDecks() {
		return decks;
	}
}
