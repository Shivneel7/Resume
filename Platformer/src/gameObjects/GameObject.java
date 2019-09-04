package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import framework.Constants;

public abstract class GameObject implements Constants{
	protected float x, y, dx, dy;
	protected Random r;
	protected ID id;

	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		r = new Random();
	}
	public abstract void tick(LinkedList<GameObject> objects);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public boolean checkBounds(GameObject temp) {
		if(getBounds().intersects(temp.getBounds())) {
			return true;
		}
		return false;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public ID getID() {
		return id;
	}

	public void setID(ID id) {
		this.id = id;
	}
}
