package game;

public class Camera {
	private float x;
	private float y;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick (GameObject player) {
		//x = -player.getX() + Game.WIDTH/2;
		//y = -player.getY() + Game.HEIGHT/2;
		x -= ((player.getX()-(Game.WIDTH/2)) + x) * .1f;
		y -= ((player.getY()-(Game.HEIGHT/2)) + y) * .1f;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
