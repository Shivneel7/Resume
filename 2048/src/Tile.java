import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Tile implements Constants{

	private int number;
	private int x,y;
	
	private int changeY, changeX;

	private Color c;

	private Random r = new Random();

	public Tile(int row, int col, int number) {
		this.x = col * TILE_LENGTH;
		this.y = row * TILE_LENGTH;
		this.number = number;
	}

	public Tile (int row, int col) {
		this(row,col,0);
	}
	
	public void tick() {
		if(changeY>0) {
			y--;
			changeY--;
		}
	}
	
	public void render(Graphics g) {
		if(number != 0) {
		g.setColor(getColor());
		//g.fillRect(col * TILE_LENGTH, row * TILE_LENGTH, TILE_LENGTH, TILE_LENGTH);
		
		g.fillRect(x, y, TILE_LENGTH, TILE_LENGTH);
			if(number == 2 || number == 4) {
				g.setColor(new Color(117,101,87));
			}else {
				g.setColor(Color.white);
			}
			g.setFont(new Font(null, 0, 40));
			//g.drawString("" + number, col * TILE_LENGTH + TILE_LENGTH/2 - 15, row * TILE_LENGTH + TILE_LENGTH/2 - 20);
			g.drawString("" + number, x + TILE_LENGTH/2 - 15, y + TILE_LENGTH/2 - 20);
			}
		g.setColor(Color.BLACK);
		//g.drawRect(col * TILE_LENGTH, row * TILE_LENGTH, TILE_LENGTH, TILE_LENGTH);
		g.drawRect(x, y, TILE_LENGTH, TILE_LENGTH);
//		g.setFont(new Font(null, 0, 40));
//		g.drawString(toString(), col * TILE_LENGTH, row * TILE_LENGTH + TILE_LENGTH/2 - 20);
		
	}

	private Color getColor() {
		switch(number) {
		case 2: 
			return new Color(250,231,224);
		case 4:
			return new Color(246,232,206);
		case 8:
			return new Color(252,176,126);
		case 16:
			return new Color(245,149,99);
		case 32:
			return new Color(246,124,95);
		case 64:
			return new Color(246,94,59);
		case 128:
			return new Color(237,207,114);
		case 256:
			return new Color(113,181,219);
		case 512:
			return new Color(243,75,92);
		case 1024:
			return new Color(0,127,194);
		case 2048:
			return new Color(251,197,45);
		case 4096:
			return new Color(244,102,116);
		default:
			return new Color(238,228,218);
		}
	}
	
	public void setNumber(int n) {
		number = n;
	}

	public int getNumber() {
		return number;
	}

	public int getRow() {
		return y / TILE_LENGTH;
	}

	public void setRow(int row) {
		this.y = row * TILE_LENGTH;
	}

	public int getCol() {
		return x / TILE_LENGTH;
	}

	public void setCol(int col) {
		this.x = col * TILE_LENGTH;
	}
	
	public String toString(){
		return "(" + getRow() + "," + getCol() + ") N: " + number;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void changeY(int y) {
		changeY = this.y-y;
	}
}
