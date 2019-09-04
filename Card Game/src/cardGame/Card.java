package cardGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Card implements Constants{

	private int x, y, number;
	private Suit suit;

	private boolean revealed;
	private boolean isTop;
	private boolean wastepile;
	
	private BufferedImage img;
	
	//true if any card is held
	public static boolean held;
	
	public Card(int x, int y, Suit suit, int number, BufferedImage i) {
		this.x = x;
		this.y = y;
		this.suit = suit;
		this.number = number;
		this.img = i;
	}

	public void tick() {
		if(isTop && !held) {
			revealed=true;
		}
	}
	
	public void render(Graphics g) {
		
		if(revealed) {
			//cardImage
			g.drawImage(img, x, y, CARD_WIDTH, CARD_HEIGHT, null);
			
			//card back
//			g.setColor(Color.white);
//			g.fillRect(x, y, CARD_WIDTH, CARD_HEIGHT);
			
			//card text
//			g.setColor(suit.color());
//			g.drawString("" +suit+ " " +number, x + 15, y + 13);
			
			//card border
//			g.setColor(Color.black);
//			g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);

		}else {
			g.setColor(Color.black);
			g.fillRect(x, y, CARD_WIDTH, CARD_HEIGHT);
			g.setColor(Color.white);
			g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
		}
		
		//bounds
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.blue);
//		g2d.draw(getBounds());
	}
	
	public Rectangle getBounds() {
		if(isTop)
			return new Rectangle(x ,y ,CARD_WIDTH, CARD_HEIGHT);
		else if(wastepile)
			return new Rectangle(x ,y , WASTEPILE_SPACING, CARD_HEIGHT);
		else 
			return new Rectangle(x ,y ,CARD_WIDTH, STACK_SPACING);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void hide() {
		revealed = false;
	}

	public void reveal() {
		revealed = true;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public int getNumber() {
		return number;
	}
	
	public boolean isWastepile() {
		return wastepile;
	}

	public void setWastepile(boolean wastepile) {
		this.wastepile = wastepile;
	}

	public String toString() {
		return "suit=" + suit + ", number=" + number;
	}

	
}
