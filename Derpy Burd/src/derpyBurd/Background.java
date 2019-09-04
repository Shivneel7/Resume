package derpyBurd;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Background {
	
	public static BufferedImage img;
	private int x;
	private int originalX;
	private int speed;
	private boolean imageNotFound = false;
	
	public Background(int x, int speed) {
		try {
			img = ImageIO.read(getClass().getResource("/original background.png"));
			//img = ImageIO.read(getClass().getResource("/res/background.png"));
		} catch (Exception e) {
			imageNotFound = true;
		}
		
		this.x = x;
		originalX =x;
		this.speed = speed;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void tick() {
		x -= speed;
		if(Math.abs(x) % Game.WIDTH < 3) {
			x = originalX;
		}
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void render(Graphics g) {
		g.drawImage(img, x, 0, null);
		
		if(imageNotFound) {
			g.setFont(new Font(null,1,20));
			g.drawString("Background Image not found,contact Shivneel for assistance", 0, 245);
		}
	}
}

