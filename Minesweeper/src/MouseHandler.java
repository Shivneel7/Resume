import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter implements Constants {

	public Window window;
	public Board b;
	public Tile[][] board;

	public MouseHandler(Window window, Board b) {
		this.window = window;
		this.b = b;
		this.board = b.getBoard();
	}

	public void mousePressed(MouseEvent e) {
		int row = e.getY() / TILE_LENGTH;
		int col = e.getX() / TILE_LENGTH;

		if (row < Game.numRow && col < Game.numCol) {
			Tile temp = board[row][col];
			if (!temp.isRevealed()) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (!temp.hasFlag()) {
						b.click(row, col);
					}
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					if (temp.hasFlag()) {
						b.decFlags();
					} else {
						b.incFlags();
					}
					temp.flag();
					if (Game.numMines == b.getNumFlags()) {
						b.checkWin();
					}
				}
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		// System.out.println(e.getPoint());
	}
}
