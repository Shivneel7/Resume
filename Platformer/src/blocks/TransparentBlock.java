package blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import gameObjects.GameObject;
import gameObjects.ID;

public class TransparentBlock extends GameObject{

	public TransparentBlock(float x, float y, ID id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> objectList) {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int) x, (int) y, BLOCK_WIDTH, BLOCK_HEIGHT);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int)y,BLOCK_WIDTH,BLOCK_HEIGHT);
	}
	
}