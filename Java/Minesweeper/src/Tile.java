import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Tile implements Constants {

	private int x, y, proximity = 0;
	private boolean mined, revealed, flag;

	public Tile(int row, int col, boolean mined) {
		this.x = col * TILE_LENGTH;
		this.y = row * TILE_LENGTH;
		this.mined = mined;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		if (revealed) {
			g.setColor(Color.WHITE);
			g.drawRect(x, y, TILE_LENGTH, TILE_LENGTH);
			g.setColor(Color.RED);
			if (mined) {
				g.fillOval(x, y, TILE_LENGTH, TILE_LENGTH);
			} else if (proximity > 0) {
				g.setColor(new Color(Game.clamp(0, 255, proximity * 50), proximity, 10));
				g.setFont(new Font(null, 0, TILE_LENGTH));
				g.drawString("" + proximity, x + 5, y + TILE_LENGTH - 2);
			}
		} else {
			g.setColor(Color.GRAY);
			g.fillRect(x, y, TILE_LENGTH, TILE_LENGTH);
			g.setColor(Color.white);
			g.drawRect(x, y, TILE_LENGTH, TILE_LENGTH);
			if (flag) {
				g.setColor(Color.black);
				g.setFont(new Font(null, 0, TILE_LENGTH));
				g.drawString("F", x + 2, y + TILE_LENGTH);
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean hasMine() {
		return mined;
	}

	public void setMine(boolean mine) {
		this.mined = mine;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean clicked) {
		this.revealed = clicked;
	}

	public void reveal() {
		revealed = true;
	}

	public int getProximity() {
		return proximity;
	}

	public void flag() {
		flag = !flag;
	}

	public boolean hasFlag() {
		return flag;
	}

	public void setProximity(int proximity) {
		this.proximity = proximity;
	}

	public void incProx() {
		this.proximity++;
	}
}
