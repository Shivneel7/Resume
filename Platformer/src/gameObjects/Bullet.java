package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Bullet extends GameObject{

	public Bullet(float x, float y, ID id, float dx, float dy) {
		super(x, y, id);
		this.dx = dx;
		this.dy = dy;
	}

	public void tick(LinkedList<GameObject> objects) {
		x += dx;
		y += dy;
		if(dx == 0) {
			objects.remove(this);
		}
		for(int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			if(temp.getID() == ID.Block) {
				if(this.getBounds().intersects(temp.getBounds())) {
					objects.remove(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(BULLET_COLOR);
		g.fillRect((int)x, (int)y, BULLET_WIDTH, BULLET_HEIGHT);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, BULLET_WIDTH, BULLET_HEIGHT);
	}
	
}
