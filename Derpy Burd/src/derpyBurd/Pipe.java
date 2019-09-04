package derpyBurd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Pipe {
	
	private int x, y;
	private Background b;
	private int pipeWidth = 40;
	private int gap = 90;
	
	Random r = new Random();
	
	public Pipe(Background b) {
		this.b = b;
		this.x = b.getX() - 60;
		getRandomY();
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Rectangle getBounds1() {
		return new Rectangle(x , 0, pipeWidth, Game.HEIGHT - y);
	}
	
	public Rectangle getBounds2() {
		return new Rectangle(x, Game.HEIGHT - y + gap, pipeWidth, y - gap);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, 0, pipeWidth, Game.HEIGHT - y);
		g.fillRect(x, Game.HEIGHT - y + gap, pipeWidth, y - gap);
	}
	
	public void getRandomY() {
		y = r.nextInt(Game.HEIGHT/2 - 5) + Game.HEIGHT/2;
	}
	
	public void tick() {
		this.x = b.getX() - pipeWidth;
		if(x == (Game.WIDTH - pipeWidth)) {
			getRandomY();
		}
	}
}