package cardGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter implements Constants {
	
	private Deck[] decks;
	private Deck held;
	private Deck stock;
	private Deck wastePile;
	private Deck lastDeck;
	
	public MouseHandler(Handler handler) {
		resetDecks(handler);
	}

	public void mousePressed(MouseEvent e) {
		if(stock.getBounds().contains(e.getPoint())) {
			if(stock.isEmpty()) {
				stock.moveAllCards(wastePile);
			}else {
				for(int i = 0; i < 3; i++) {
					if(stock.deck.size() >0)
						wastePile.addCard(stock.takeTopCard());
				}
			}
		}else if(wastePile.getBounds().contains(e.getPoint()) && !wastePile.isEmpty()){
			held.addCard(wastePile.takeBottomCard());
			lastDeck = wastePile;
			Deck.canWastePileReset = false;
		}else {
			for(int i = 6; i <= 12; i++) {
				Deck tableau = decks[i];
				if(!tableau.isEmpty()) {
					if(tableau.getBounds().contains(e.getPoint())) {
						for(int j = tableau.deck.size() - 1; j >= 0  ; j--) {
							Card tempCard = tableau.deck.get(j);
							if(tempCard.getBounds().contains(e.getPoint()) && tempCard.isRevealed()) {
								int iterations = tableau.deck.size() - tableau.deck.indexOf(tempCard) - 1;
								for(int k = 0; k < iterations; k ++) {
									held.deck.add(0, tableau.takeBottomCard());
								}
								held.deck.add(0, tableau.deck.remove(tableau.deck.indexOf(tempCard)));
							}
						}
						lastDeck = tableau;
					}
				}
			}
		}
		if(!held.isEmpty()) {
			Card.held = true;
			held.setX(e.getX()- CARD_WIDTH/2);
			held.setY(e.getY());
		}
	}

	public void mouseReleased(MouseEvent e) {
		for(int i = 0; i < decks.length-1; i++) {
			Deck tempDeck = decks[i];
			if(tempDeck.getBounds().contains(e.getPoint()) && tempDeck.getID()!= DeckID.STOCK
					&& tempDeck.getID()!= DeckID.WASTEPILE) {
				if(!held.isEmpty()) {
					checkRules(tempDeck);
				}
			}
		}
		if(!held.isEmpty()) {
			lastDeck.moveAllCards(held);
		}
		held.setX(GAME_WIDTH);
		Deck.canWastePileReset = true;
	}

	public void checkRules(Deck tempDeck){
		if(tempDeck.getID() == DeckID.TABLEAU) {
			if(tempDeck.isEmpty()) {
				if(held.getTopCard().getNumber() == 13) {
					tempDeck.moveAllCards(held);
					Card.held = false;
				}
			}else if(tempDeck.getBottomCard().getNumber() == held.getTopCard().getNumber() + 1 
					&& tempDeck.getBottomCard().getSuit().color() != held.getTopCard().getSuit().color()) {
				//tableaus
				tempDeck.moveAllCards(held);
				Card.held = false;
			}
		}else if(tempDeck.getID() == DeckID.FOUNDATION) {
			if(tempDeck.isEmpty()) {
				if(held.getTopCard().getNumber() == 1) {
					tempDeck.addCard(held.takeTopCard());
					Card.held = false;
				}
			}else if(tempDeck.getBottomCard().getSuit() == held.getTopCard().getSuit()
					&& tempDeck.getBottomCard().getNumber() == held.getTopCard().getNumber() - 1){
				tempDeck.addCard(held.takeTopCard());
				Card.held = false;
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if(!held.isEmpty()) {
			held.setX(e.getX()- CARD_WIDTH/2);
			held.setY(e.getY());
		}
	}
	
	public void resetDecks(Handler handler) {
		decks = handler.getDecks();
		held = decks[13];
		stock = decks[0];
		wastePile = decks[1];
	}
}
