package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import framework.Constants;
import framework.Game;
import framework.Handler;
import framework.Game.STATE;
import gameObjects.Bullet;
import gameObjects.GameObject;
import gameObjects.ID;
import gameObjects.Knife;
/**
 * Handles all mouse events, not just menu.
 * @author shivn
 *
 */
public class Menu extends MouseAdapter implements Constants{
	
	private Handler handler;
	private HUD hud;
	
	public Menu(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	public void tick() {
		//What happens when we lose
		if(Game.gameState == STATE.Loss) {
			loss();
		}
	}
	
	/**
	 * What happens when player loses.
	 */
	private void loss() {
		hud.resetLives();
		hud.increaseScore(-500);
		
		handler.switchLevel();
		if(handler.player.getCP() != null) {
			handler.player.setX(handler.player.getCP().getX());
			handler.player.setY(handler.player.getCP().getY());
		}
		if(handler.player.knife!=null) {
			handler.addObject(handler.player.knife);
		}
		Game.gameState = STATE.Game;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(Game.gameState == STATE.Game) {
				if(e.getButton() == MouseEvent.BUTTON1){
					if(handler.player.knife!=null) {
						handler.player.knife.click();
					}
					else{
						handler.player.punch();
					}
				}
			if(hud.doesPlayerHasGun() && hud.getAmmo() > 0 &&  e.getButton() == MouseEvent.BUTTON3) {
				handler.addObject(new Bullet(handler.player.getX() + PLAYER_WIDTH/2, 
						handler.player.getY() + PLAYER_HEIGHT/5 * 2, ID.PlayerBullet,
						BULLET_SPEED * (handler.player.getDirection()), 0));
				hud.increaseAmmo(-1);
			}
		}else if(Game.gameState == STATE.Menu) {
			if(getClick(mx, my, 100, 100, 200, 200)) {
				Game.gameState = STATE.Game;

			}else if(getClick(mx, my, 100,400,100,50)) {
				handler.player.setInvincible(true);
				
			}else if(getClick(mx, my, 300,400,100,50)) {
				handler.player.setFly(true);
				handler.player.setNoClip(false);
				
			}else if(getClick(mx, my, 500,400,100,50)) {
				handler.player.setFly(true);
				handler.player.setNoClip(true);
				
			}else if(getClick(mx, my, 400,300,50,50)) {
				handler.player.setInvincible(false);
				handler.player.setFly(false);
				handler.player.setNoClip(false);
			}
		}else if(Game.gameState == STATE.Loss) {
			if(getClick(mx, my, 200, 200, 200, 200)) {
				Game.gameState = STATE.Game;
			}
		}
	}

	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
			g.setColor(Color.white);
			g.setFont(new Font(null, 1, 20));
			
			g.drawRect(100, 100, 200, 200);
			g.drawString("Play", 100, 100);
			
			g.drawRect(100,400,100,50);
			g.drawString("Invincible" , 100, 500);
			
			g.drawRect(300,400,100,50);
			g.drawString("Fly" , 300, 500);
			
			g.drawRect(500,400,100,50);
			g.drawString("Fly + no Clip" , 500, 500);
			
			g.drawRect(400,300,50,50);
			g.drawString("No Cheats" , 400, 300);
			
		}else if(Game.gameState == STATE.Loss) {
			g.setColor(Color.white);
			g.setFont(new Font(null, 1, 90));
			g.drawString("U Looz" , 0, GAME_HEIGHT /2 );
			g.drawRect(200, 200, 200, 200);
		}
	}

	public boolean getClick(int mx, int my, int x, int y, int width, int height) {
		if(mx < width + x && mx > x && my < height+y && my > y) {
			return true;
		}else return false;
	}
	
}
