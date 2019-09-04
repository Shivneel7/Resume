package cardGame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Deck implements Constants{

	private int x,y, width = CARD_WIDTH, height = CARD_HEIGHT;
	private boolean stackable;
	public static boolean canWastePileReset;
	private DeckID id;
	
	public ArrayList<Card> deck;
	
	public Deck(int x, int y, DeckID id) {
		deck = new ArrayList<>();
		this.x = x;
		this.y = y;
		this.id = id;
		this.stackable = id.stackable();
	}

	public void tick() {
		
		for(int i = 0; i < deck.size(); i++) {
			deck.get(i).tick();
			if(stackable) {
				deck.get(i).setY(y+ STACK_SPACING *i);
			}
			else {
				deck.get(i).setY(y);
			}
			if(id == DeckID.WASTEPILE) {
				if(canWastePileReset) {
					if(i == deck.size()-1) {
						deck.get(i).setX(x + WASTEPILE_SPACING * 2);
					}else if(i == deck.size()-2) {
						deck.get(i).setX(x + WASTEPILE_SPACING);
					}else {
						deck.get(i).setX(x);
					}
				}
				deck.get(i).reveal();
				deck.get(i).setWastepile(true);
			}else {
				deck.get(i).setX(x);
				deck.get(i).setTop(false);
				deck.get(i).setWastepile(false);
			}
			if(id == DeckID.STOCK) {
				deck.get(i).hide();
			}
		}
		
		if(deck.size()>0) {
			getBottomCard().setTop(true);
			if(stackable) {
				height = CARD_HEIGHT + ((deck.size()- 1) * STACK_SPACING);
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(x, y, width, CARD_HEIGHT);
		for(int i = 0; i < deck.size(); i ++) {
			deck.get(i).render(g);
		}
		
		//bounds
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.blue);
//		g2d.draw(getBounds());
	}
	
	public void addCard(Card c) {
		deck.add(c);
	}
	
	public void moveAllCards(Deck d) {
		deck.addAll(d.deck);
		d.clear();
	}
	
	public Card takeCard(int index) {
		return deck.remove(index);
	}
	
	public Card takeBottomCard() {
		return takeCard(deck.size()-1);
	}
	
	public Card takeTopCard() {
		return takeCard(0);
	}
	
	public Card getBottomCard() {
		return deck.get(deck.size()-1);
	}
	
	public Card getTopCard() {
		return deck.get(0);
	}
	
	public void clear() {
		deck.clear();
	}
	
	public Rectangle getBounds() {
		if(id == DeckID.WASTEPILE)
			return new Rectangle(x ,y, WASTEPILE_SPACING * 2 + CARD_WIDTH, height);
		else
			return new Rectangle(x ,y, width, height);
	}
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public DeckID getID() {
		return id;
	}

	public void setId(DeckID id) {
		this.id = id;
	}

	public String toString() {
		return ""+deck;
	}
}
