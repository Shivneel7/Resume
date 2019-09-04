package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Player extends GameObject{

	private BufferedImageLoader loader;
	private SpriteSheet ss;
	
	private int width = 32, height = 64;
	
	
	private float gravity = .5f;
	protected boolean jumping = false;
	protected boolean falling = false;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		loader = new BufferedImageLoader();
		 ss = new SpriteSheet(loader.loadImage("/Untitled.png"));
	}

	public void tick(LinkedList<GameObject> object) {
		x += dx;
		y += dy;
		if(falling) {
			dy += gravity;
		}
		collision(object);
	}
	
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	private void collision(LinkedList<GameObject> object) {
		
		for(int i = 0; i < object.size(); i ++) {
			if(object.get(i).getID() == ID.Block) {
				if(object.get(i).getBounds().intersects(getBounds())) {
					y = object.get(i).getY() - height;
					dy = 0;
					falling = false;
					jumping = false;
				}else {
					falling = true;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,width,height);
	}
	

}