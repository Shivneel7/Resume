package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Coin extends GameObject{

	public Coin(float x, float y, ID id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> objects) {

	}

	public void render(Graphics g) {
		g.setColor(COIN_COLOR);
		g.fillOval((int)x, (int)y+8, 24, 24);
		g.setColor(Color.blue);
		g.drawString("N" , (int)x + 7, (int)y + 24);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y+8, 24, 24);
	}
}
