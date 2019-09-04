package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Upgrade extends GameObject {
	
	private int width = 24, height = 24;
	private float gravity = GRAVITY;
	private boolean falling;
	
	public Upgrade(float x, float y, ID id, float dx) {
		super(x, y, id);
		this.dx = dx;
	}

	public void tick(LinkedList<GameObject> objects) {
		x += dx;
		y += dy;
		
		if(falling) {
			dy += gravity;
		}
		
		for(int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			if(temp.getID() == ID.Block || temp.getID() == ID.DeathBlock || temp.getID() == ID.TransparentBlock) {
				normalBlockCollision(temp);
			}
		}
	}
	
	private void normalBlockCollision(GameObject block) {
		if(this.getBounds().intersects(block.getBounds())) {
			y = block.getY() - height;
			dy = 0;
			falling = false;
		}else {
			falling = true;
		}
		if(this.getBoundsLeft().intersects(block.getBounds())) {
			x = block.getX() + BLOCK_WIDTH;
			dx*=-1;
		}
		if(this.getBoundsRight().intersects(block.getBounds())) {
			x = block.getX() - width;
			dx*=-1;
		}
	}
	
	public boolean checkAllBounds(GameObject temp) {
		if(this.getBounds().intersects(temp.getBounds()) ||
				this.getBoundsTop().intersects(temp.getBounds()) ||
				this.getBoundsRight().intersects(temp.getBounds()) ||
				this.getBoundsLeft().intersects(temp.getBounds())) {
			return true;
		}
		return false;
	}
	
	public void render(Graphics g) {
		if(id == ID.HealthUpgrade) {
			g.setColor(Color.cyan);
			g.fillRect((int)x, (int) y, width, height);
			g.setColor(Color.darkGray);
			g.setFont(new Font(null, 1, 24));
			g.drawString("H", (int) x + 4, (int) y + 20);
		}else if(id == ID.AmmoUpgrade) {
			g.setColor(Color.cyan);
			g.fillRect((int)x, (int) y, width, height);
			g.setColor(Color.darkGray);
			g.setFont(new Font(null, 1, 24));
			g.drawString("A", (int) x + 4, (int) y + 20);
		}
		
		//Bounding Boxes
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x+8, (int)y + height/2, width-16, height/2);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x+8, (int)y, width-16, height/2);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int)y + 4, width/2 - 6, height - 8);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + width/2 + 6, (int)y + 4, width/2 - 7, height - 8);
	}
}
