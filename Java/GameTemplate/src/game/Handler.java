package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			object.get(i).tick(object);
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			object.get(i).render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	public void createLevel() {
		for(int i = 0; i < Game.WIDTH * 7; i+=32) {
			addObject(new Block(i, Game.HEIGHT-32,ID.Block));
		}
	}
}
