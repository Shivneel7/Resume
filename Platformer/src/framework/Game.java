package framework;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import userInterface.HUD;
import userInterface.Menu;

public class Game extends Canvas implements Runnable, Constants{
	
	private static final long serialVersionUID = -3307397034749894482L;
	
	Thread thread;
	boolean running = false;
	
	private HUD hud;
	public Handler handler;
	private Camera cam;
	private Menu menu;
	
	public enum STATE {Game, Menu, Loss}
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		hud = new HUD();
		handler = new Handler(hud);
		handler.switchLevel();
		menu = new Menu(handler, hud);
		
		cam = new Camera(-1000, 0);
		new Window(GAME_WIDTH, GAME_HEIGHT, TITLE , this);
		
		this.addMouseListener(menu);
		this.addKeyListener(new KeyInput(handler));
	}

	public static void main(String[] args) {
		new Game();
	}

	public synchronized void start() {
		if(!running) {
			thread = new Thread(this);
			thread.start();
			running = true;
		}else
			return;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		/////////////////////////////////////////////////////
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		
		if(gameState == STATE.Game) {

		g2d.translate(cam.getX(), cam.getY());//camera
		
		handler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY());//camera
		
		hud.render(g);
		
		}else if(gameState == STATE.Menu || gameState == STATE.Loss) {
			menu.render(g);
		}
		
		/////////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public void tick() {
		if(gameState == STATE.Game) {
		handler.tick();
		hud.tick();
		cam.tick(handler.player);
		}else if(gameState == STATE.Menu || gameState == STATE.Loss) {
			menu.tick();
		}
	}
	
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
        	long now = System.nanoTime();
        	delta += (now - lastTime) / ns;
        	lastTime = now;
        	while(delta >=1) {
        		tick();
        		delta--;
        	}
        	if(running) {
        		render();
        	}
        	frames++;
        	if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: "+ frames);
				frames = 0;
        	}
        }
        stop();
	}
	
	public static int clamp(int x, int min, int max) {
		if(x < min) 
			return min;
		if(x > max)
			return max;
		return x;
	}
}
