package framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import blocks.Block;
import blocks.Checkpoint;
import blocks.DeathBlock;
import blocks.TransparentBlock;
import enemies.Enemy;
import enemies.Jumper;
import enemies.Shooter;
import enemies.SmartEnemy;
import enemies.SmartJumper;
import framework.Game.STATE;
import gameObjects.Coin;
import gameObjects.GameObject;
import gameObjects.Goal;
import gameObjects.ID;
import gameObjects.Knife;
import gameObjects.Player;
import userInterface.HUD;
/**
 * Handles all GameObjects and creates and keeps track of levels.
 * 
 *Also where lives are checked.
 */
public class Handler {
	
	public static int LEVEL = 0;
	
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	private BufferedImage[] levels = new BufferedImage[3];
	
	private HUD hud;
	public Player player;
	private Random r;
	
	public Handler(HUD hud){
		this.hud = hud;
		BufferedImageLoader loader = new BufferedImageLoader();
		r = new Random();
		try {
			Scanner levelReader = new Scanner(new File("res/lev.txt"));
			if(levelReader.hasNextInt()) LEVEL = levelReader.nextInt();
			levelReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < levels.length; i++) {
			levels[i] = loader.loadImage("/level"+ (i + 1) +".png");
		}
	}
	
	public void tick() {
		if(hud.getHealth() == 0) {//checks lives
			Game.gameState = STATE.Loss;
		}
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).tick(objects);
		}
		player.tick(objects);
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getID() == ID.Block || objects.get(i).getID() == ID.Checkpoint) 
				objects.get(i).render(g);
		}
		player.render(g);
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getID() != ID.Block && objects.get(i).getID() != ID.Checkpoint) 
				objects.get(i).render(g);
		}
	}

	public void switchLevel() {
		clearLevel();
		loadLevel(levels[LEVEL]);
	}


	private void clearLevel() {
		objects.clear();
	}

	public void addObject(GameObject object) {
		this.objects.add(object);
	}

	public void removeObject(GameObject object) {
		this.objects.remove(object);
	}
	
	public void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16 ) & 0xff;
				int green = (pixel >> 8 ) & 0xff;
				int blue = (pixel) & 0xff;
				//if statements determining objects:
				
				//normal blocks
				if(red == 255 && green == 255 & blue == 255) {
					addObject(new Block(xx*32, yy*32, ID.Block));
				}
				if(red == 0 && green == 0 & blue == 255) {
					if(player == null) {
						player = new Player(xx*32, yy*32, ID.Player, this, hud);
					}else {
						player.setX(xx*32);
						player.setY(yy*32);
					}
				}
				//Other BLOCKS
				if(red == 255 && green == 255 & blue == 0) {
					addObject(new Checkpoint(xx*32, yy*32, ID.Checkpoint));
				}
				if(red == 255 && green == 0 & blue == 0) {
					addObject(new DeathBlock(xx*32, yy*32, ID.DeathBlock));
				}
				if(red == 0 && green == 255 & blue == 0) {
					addObject(new Goal(xx*32, yy*32, ID.Goal));
				}
				if(red == 128 && green == 128 & blue == 128) {
					addObject(new TransparentBlock(xx*32, yy*32, ID.TransparentBlock));
				}
				
				//ENEMIES
				if(red == 255 && green == 50 & blue == 50) {
					if(r.nextBoolean()) 
						addObject(new Enemy(xx*32, yy*32, ID.Enemy, -3, hud, player));
					else
						addObject(new Enemy(xx*32, yy*32, ID.Enemy, 3, hud, player));
				}
				if(red == 255 && green == 100 & blue == 100) {
					addObject(new Shooter(xx*32, yy*32, ID.Shooter, -2, hud, player));
				}
				if(red == 255 && green == 200 & blue == 50) {
					addObject(new SmartEnemy(xx*32, yy*32, ID.SmartEnemy, -2, hud, player));
				}
				if(red == 255 && green == 50 & blue == 200) {
					addObject(new Jumper(xx*32, yy*32, ID.Jumper, -2, hud, player));
				}
				if(red == 255 && green == 200 & blue == 200) {
					addObject(new SmartJumper(xx*32, yy*32, ID.SmartJumper, -2, hud, player));
				}
				
				//Other
				if(red == 200 && green == 120 & blue == 0) {
					addObject(new Coin(xx*32, yy*32, ID.Coin));
				}
				if(red == 0 && green == 100 & blue == 100) {
					addObject(new Knife(xx*32, yy*32, ID.Knife, player));
				}

			}
		}
	}
}
