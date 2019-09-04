package game;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {
	protected float x, y, dx, dy;
	protected ID id;

	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x) {
		this.x = x;
	}
	
	public float getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public float getY() {
		return y;
	}
	
	public void setID(ID id) {
		this.id = id;
	}

	public ID getID() {
		return id;
	}
	
	public void setdx(int dx) {
		this.dx = dx;
	}
	
	public float getdx() {
		return dx;
	}
	
	public void setdy(int dy) {
		this.dy = dy;
	}
	
	public float getdy() {
		return dy;
	}
	
}