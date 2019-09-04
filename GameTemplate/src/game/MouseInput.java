package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	
	public MouseInput(Handler handler) {
		this.handler = handler;
	}

	public void mouseMoved(MouseEvent e){
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Player) {
				handler.object.get(i).setX(e.getX());
				handler.object.get(i).setY(e.getY());
			}
		}
		
	}
	
	//Checks if mouse was in a rectangle
	public boolean getClick(int mx, int my, int x, int y, int width, int height) {
		if(mx < width + x && mx > x && my < height+y && my > y) {
			return true;
		}else return false;
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getPoint().toString());
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println(e.getWheelRotation());
	}
}
