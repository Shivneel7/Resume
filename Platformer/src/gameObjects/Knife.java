package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.BufferedImageLoader;
import framework.SpriteSheet;

public class Knife extends GameObject {
	
	private SpriteSheet ss;
	private BufferedImage stillRight;
	private BufferedImage stillLeft;
	private BufferedImage hitRight;
	private BufferedImage hitLeft;

	private Player player;
	
	private boolean click = false, right = true;

	private int clickCounter = 0;
	
	public Knife(float x, float y, ID id, Player p) {
		super(x, y, id);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		ss = new SpriteSheet(loader.loadImage("/spriteSheet.png"));
		stillRight = ss.grabImage(2, 2, 16, 16);
		stillLeft = ss.grabImage(1, 2, 16, 16);
		hitRight = ss.grabImage(1, 4, 32, 16);
		hitLeft = ss.grabImage(1, 3, 32, 16);
		
		player = p;
	}

	public void tick(LinkedList<GameObject> objects) {
		if(click) {
			clickCounter++;
		}
		if(clickCounter > CLICK_SPEED) {
			click = false;
			clickCounter = 0;
		}
		
		if(player.getDirection() < 0) 
			right = false;
		else right = true;
	}

	public void render(Graphics g) {
		if(click && right) {
			g.drawImage(hitRight, (int) x, (int) y + 20, 72, 48, null);
		}else if(!click && right){
			g.drawImage(stillRight, (int) x, (int) y, 48, 48, null);
		}else if(click && ! right) {
			g.drawImage(hitLeft, (int) x - 45, (int) y + 20, 72, 48, null);
		}else {
			g.drawImage(stillLeft, (int) x- PLAYER_WIDTH, (int) y, 48, 48, null);
		}

		//bounds
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.red);
//		g2d.draw(getBounds());
	}
	
	public void click() {
		click = true;
	}
	
	public boolean getClick() {
		return click;
	}
	
	public Rectangle getBounds() {
		if(this.id == ID.Knife) {
			return new Rectangle((int) x, (int) y, 48, 48);
		}else if(click && right) {
			return new Rectangle((int) x + 22, (int) y + 23, 40, 20);
		}else if(!click && right){
			return new Rectangle((int) x, (int) y +  28, 12, 12);
		}else if(click && ! right) {
			return new Rectangle((int) x - 46, (int) y + 23, 40, 20);
		}else {
			return new Rectangle((int) x, (int) y +  26, 12, 12);
		}
	}
	
}
