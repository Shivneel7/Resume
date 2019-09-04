package cardGame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JMenuBar;

public class Game extends Canvas implements Runnable, Constants, ActionListener {

	private static final long serialVersionUID = -8921419424614180143L;
	
	private Thread thread;
	private boolean running = false;
	
	private JMenuBar menuBar;
	private Handler handler;
	private MouseHandler mouse;
	
	public Game() {
		handler = new Handler();
		menuBar = new Window("Shiv's Salty Solitaire", GAME_WIDTH, GAME_HEIGHT, this).getMenuBar();
		
		menuBar.getMenu(0).getItem(0).addActionListener(this);
		menuBar.getMenu(0).getItem(2).addActionListener(this);
		
		mouse = new MouseHandler(handler);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public void tick() {
		handler.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////////////////////
		g.setColor(Color.green);
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		
		handler.render(g);
		
		/////////////////////////////////////////////////////
		g.dispose();
		bs.show();
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
				// System.out.println("FPS: "+ frames);
				frames = 0;
        	}
        }
        stop();
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("restart")) {
			handler.resetBoard();
			mouse.resetDecks(handler);
			Card.held = false;
		}else if(e.getActionCommand().equals("hint")) {
			System.out.println("hint");
			autoComplete();
		}
	}
	
	//AUTO COMPLETE CODE
	public void autoComplete() {
		//for loop checks if any tableau can be placed in the foundations
		//check if top of tableau can move to other tableau
		//click stock and see if the stock can move to the foundations, then the tableau
		//repeat or go to stock depending on outcome of last step
		
	}
}
