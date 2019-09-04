package enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.Game;
import gameObjects.GameObject;
import gameObjects.ID;
import gameObjects.Knife;
import gameObjects.Player;
import gameObjects.Upgrade;
import userInterface.HUD;
/**
 * Just moves back and forth when it hits a block.
 * @author shivn
 *
 */
public class Enemy extends GameObject{
	
	private int width = ENEMY_WIDTH, height = ENEMY_HEIGHT, health = ENEMY_HEALTH;
	private float gravity = GRAVITY;

	private int invincibleTimer = 0;
	private boolean falling = true, invincible;
	
	private HUD hud;
	private Player player;
	private Color c;
	
	public Enemy(float x, float y, ID id, float dx, HUD hud, Player player) {
		super(x, y, id);
		this.dx = dx;
		this.hud = hud;
		this.player = player;
		c = new Color(r.nextInt(200) + 10, r.nextInt(200) + 10, r.nextInt(200) + 10);
	}

	public void tick(LinkedList<GameObject> objects) {
		x += dx;
		y += dy;
		
		if(invincible) {
			invincibleTimer ++;
			if(invincibleTimer > 25) {
				invincible = false;
				invincibleTimer = 0;
			}
		}
		
		if(health <= 0) {
			if(r.nextBoolean()) 
				objects.add(new Upgrade(x, y, ID.HealthUpgrade, 0));
			hud.increaseScore(50);
			objects.remove(this);
		}

		if(falling) {
			dy += gravity;
		}

		for(int i = 0; i < objects.size(); i++) {//collision
			GameObject temp = objects.get(i);

			if(temp.getID() == ID.Block || temp.getID() == ID.DeathBlock || temp.getID() == ID.TransparentBlock) {
				normalBlockCollision(temp);

			}else if(temp.getID() == ID.PlayerBullet) {
				if(checkBounds(temp)) {
					objects.remove(temp);
					health--;
				}

			}else if(temp.getID() == ID.PlayerKnife && ((Knife)temp).getClick()) {
				if(checkBounds(temp)) {
					if(!invincible) {
						health -= 2;
						invincible = true;
					}
				}
			}
		}

		if(player.isPunching() && player.getBoundsFist().intersects(this.getBounds())) {
			if(!invincible) {
				health -= 2;
				invincible = true;
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
		g.setColor(c);
		if(invincible) {
			g.setColor(Color.red);
		}
		g.fillRect((int)x, (int) y, width, height);
		//face
		g.setColor(Color.white);
		g.fillRect((int)x + 6, (int) y + 8, 4, 4);
		g.fillRect((int)x + width - 8, (int) y + 8, 4, 4);
		g.drawLine((int) x, (int) y + 20, (int)x + width - 1, (int) y + 20);
		//Health
		g.setColor(new Color(125, Game.clamp(health, 0, ENEMY_HEALTH) * (255/ENEMY_HEALTH), 0));
		g.fillRect((int)x, (int)y - 10 , width * health / ENEMY_HEALTH, 5);
		
		
//		//Bounding Boxes
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.red);
//		g2d.draw(getBoundsBottom());
//		g2d.draw(getBoundsTop());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsRight());

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
		return new Rectangle((int) x, (int)y + 4, width/2 - 6, height - 8);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + width/2 + 6, (int)y + 4, width/2 - 7, height - 8);
	}
}
