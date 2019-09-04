package enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.Game;
import gameObjects.Bullet;
import gameObjects.GameObject;
import gameObjects.ID;
import gameObjects.Knife;
import gameObjects.Player;
import gameObjects.Upgrade;
import userInterface.HUD;
/**
 * Shoots bullets at the player.
 * When the players gets closer, fire rate increases.
 * Will drop ammo when killed, and give the player a gun.
 * @author Shivneel Chand
 *
 */

public class Shooter extends GameObject{

	private int width = SHOOTER_WIDTH, height = SHOOTER_HEIGHT, health = SHOOTER_HEALTH;;
	private float gravity = GRAVITY;
	
	private int invincibleTimer = 0, triggerCounter = 100;
	private boolean falling = true, invincible;

	private HUD hud;
	private Player player;


	public Shooter(float x, float y, ID id, float dx, HUD hud, Player p) {
		super(x, y, id);
		this.dx= dx;
		this.hud = hud;
		player = p;
	}

	public void tick(LinkedList<GameObject> objects) {
		x += dx;
		y += dy;
		
		if(falling ) {
			dy += gravity;
		}
		
		if(invincible) {
			invincibleTimer ++;
			if(invincibleTimer > 25) {
				invincible = false;
				invincibleTimer = 0;
			}
		}
		
		if(health <= 0) {
			hud.increaseScore(100);
			hud.setPlayerHasGun(true);
			objects.add(new Upgrade(x, y + 12, ID.AmmoUpgrade, 1));
			objects.add(new Upgrade(x, y + 12, ID.AmmoUpgrade, -1));
			objects.remove(this);
		}
		for(int i = 0; i < objects.size(); i++) {//collision
			GameObject temp = objects.get(i);
			
			if(temp.getID() == ID.Block || temp.getID() == ID.DeathBlock || temp.getID() == ID.TransparentBlock) {
				normalBlockCollision(temp);
				
			}else if(temp.getID() == ID.PlayerBullet) {
				if(checkBounds(temp)) {
					health--;
				}
			}else if(temp.getID() == ID.PlayerKnife && ((Knife)temp).getClick()) {
				if(checkBounds(temp)) {
					if(!invincible) {
						health = 0;
						invincible = true;
					}
				}
			}
		}

		if(player.isPunching() && player.getBoundsFist().intersects(this.getBounds())) {
			if(!invincible) {
				health = 0;
				invincible = true;
			}
		}
		
		//AI
		int distance = (int) Math.abs(player.getX() - x);
		if(distance < 600) {
			triggerCounter++;
			if(triggerCounter > 13 && distance < 150) {
				objects.add(new Bullet(x + width/2 , y + height/2 - 17, ID.EnemyBullet,
						Math.signum((player.getX() - x)) * BULLET_SPEED, 0));
				triggerCounter = 0;
			}else if(triggerCounter > 40 && distance < 250) {
				objects.add(new Bullet(x + width/2 , y + height/2 - 17, ID.EnemyBullet,
						Math.signum((player.getX() - x)) * BULLET_SPEED, 0));
				triggerCounter = 0;
			}else if(triggerCounter > 80){
				objects.add(new Bullet(x + width/2 , y + height/2 - 17, ID.EnemyBullet,
						Math.signum((player.getX() - x)) * (BULLET_SPEED + 1), 0));
				triggerCounter = 0;
			}
		}
	}

	private void normalBlockCollision(GameObject block) {
		if(this.getBoundsBottom().intersects(block.getBounds())) {
			y = block.getY() - height;
			dy = 0;
			falling = false;
		}else {
			falling = true;
		}
		if(this.getBoundsLeft().intersects(block.getBounds())) {
			x = block.getX() + BLOCK_WIDTH;
			dx*=-1;
		}
		if(this.getBoundsRight().intersects(block.getBounds())) {
			x = block.getX() - width;
			dx*=-1;
		}

	}

	public void render(Graphics g) {
		g.setColor(SHOOTER_COLOR);
		if(invincible) {
			g.setColor(Color.red);
		}
		g.fillRect((int)x, (int) y, width, height);
		g.setColor(Color.white);
		//face
		g.fillRect((int)x + 6, (int) y + 8, 4, 4);
		g.fillRect((int)x + width - 8, (int) y + 8, 4, 4);
		g.drawLine((int) x, (int) y + 20, (int)x + width - 1, (int) y + 20);
		//health
		g.setColor(new Color(125, Game.clamp(health, 0, SHOOTER_HEALTH) * (255/SHOOTER_HEALTH), 0));
		g.fillRect((int)x, (int)y - 10 , width * health / SHOOTER_HEALTH, 5);
		
//		//Bounding Boxes
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.red);
//		g2d.draw(getBoundsBottom());
//		g2d.draw(getBoundsTop());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsRight());

	}

	public boolean checkBounds(GameObject temp) {
		if(getBounds().intersects(temp.getBounds())) {
			return true;
		}
		return false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int)y, width, height);
	}
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x+8, (int)y + height/2, width-16, height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x+8, (int)y, width-16, height/2);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int)y + 4, width/2 - 10, height - 8);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + width/2 + 10, (int)y + 4, width/2 - 10, height - 8);
	}
}
