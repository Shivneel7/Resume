package framework;

import gameObjects.Player;

public class Camera implements Constants{
	private float x;
	private float y;
	private int floorConstant = -650 ; //0 20 blocks ,-170 25blocks,-330 30blocks,
										//-490 = 35 blocks,-650 40 blocks  
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick (Player player) {
		//x = -player.getX() + Game.WIDTH/2;
		//y = -player.getY() + Game.HEIGHT/2;
	
		if(x > -50) {//to make the camera stop at a certain point
			x = -50;
		}
		x -= ((player.getX()-(GAME_WIDTH/2)) + x) * .1f;

		if(y < floorConstant) {//to make the camera stop at a certain point
			y = floorConstant;
		}
		
		y -= ((player.getY()-(GAME_HEIGHT/2)) + y) * .1f;
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
