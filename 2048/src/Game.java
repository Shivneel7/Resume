import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable, Constants {

	private static final long serialVersionUID = -5291294102131783181L;

	Thread thread;
	private boolean running;

	Board board;

	Random r;

	public Game() {
		new Window(GAME_WIDTH, GAME_HEIGHT, "2048", this);

		board = new Board();

		this.addKeyListener(new KeyInput(board));

		r = new Random();

	}

	public static void main(String[] args) {
		new Game();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////
		
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		
		/////////////////////////////////////
		
		board.render(g);

		/////////////////////////////////////
		g.dispose();
		bs.show();
	}

	public void tick() {
		board.tick();
	}

	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: "+ frames);
				frames = 0;
			}
		}
		stop();
	}

	public synchronized void start() {
		if (!running) {
			thread = new Thread(this);
			thread.start();
			running = true;
		} else
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

	public static int clamp(int x, int max, int min) {
		if(x<min)
			return min;
		if(x> max) 
			return max;
		
		return x;
	}
}
