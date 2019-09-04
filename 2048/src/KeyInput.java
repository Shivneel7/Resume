import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	
	Board b;
	
	KeyInput(Board board){
		this.b = board;
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			b.move("up");
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			b.move("left");
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			b.move("down");
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			b.move("right");
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
