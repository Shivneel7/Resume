package ai;

import java.awt.Graphics;
import java.util.ArrayList;

import derpyBurd.Bird;
import derpyBurd.Pipe;

public class Population {
	ArrayList<Bird> pop;
	
	public Population(int size, Pipe p) {
		pop = new ArrayList<>(size);
		for(int i = 0; i < size; i++) {
			pop.add(new Bird(i, i, p));
		}
	}
	
	public void render(Graphics g) {
		for(Bird bird : pop) {
			bird.render(g);
		}
	}
	
	public void tick() {
		for(Bird bird : pop) {
			bird.tick();
		}
	}
}
