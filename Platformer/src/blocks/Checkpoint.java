package blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import gameObjects.GameObject;
import gameObjects.ID;

public class Checkpoint extends GameObject {
	public Checkpoint(float x, float y, ID id) {
		super(x, y, id);

	}

	public void tick(LinkedList<GameObject> objects) {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int) y, 32, 32);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 32, 32);
	}
}
