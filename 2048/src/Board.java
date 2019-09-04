import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board implements Constants {

	public Tile[][] board;
	private ArrayList<Tile> empty;
	private ArrayList<Tile> tiles;
	Random r;

	public Board() {
		r = new Random();
		board = new Tile[DIM][DIM];
		empty = new ArrayList<>(DIM * DIM);

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				Tile temp = new Tile(row, col);
				// Tile temp = new Tile(row, col, exp(r.nextInt(4)));
				board[row][col] = temp;
				empty.add(temp);
			}
		}
		addTile();
		addTile();
	}

	public int exp(int x) {
		int y = 2;
		for (int i = 0; i < x; i++) {
			y *= 2;
		}
		return y;
	}
	public void move(String direction) {
		if (empty.size() == 0) {
			System.out.println("You Lost M8"); // LOSE
		} else {
			boolean moved = false;
			for (int i = 0; i < DIM; i++) {
				Tile[] line = new Tile[DIM];
				for (int j = 0; j < DIM; j++) {
					if(direction.equals("up") || direction.equals("down")) {
						line[j] = board[j][i];
					}else {
						line[j] = board[i][j];
					}
				}
				if(shift(line, direction)) {
					moved = true;
				}
			}
			if(moved) {
				addTile();
			}
		}
	}

	private boolean shift(Tile[] line, String direction) {
		boolean moved = false;
		if (direction.equals("up") || direction.equals("down")) {
			int column = line[0].getCol();
			if (direction.equals("up")) {
				for (int i = 1; i < line.length; i++) {
					if (!empty.contains(line[i])) {
						Tile up = board[line[i].getRow() - 1][column];
						int j = i;
						while (empty.contains(up)) {
							up.setNumber(line[j].getNumber());
							empty.remove(up);
							line[j].setNumber(0);
							empty.add(line[j]);
							j--;
							moved = true;
							if (up.getRow() == 0) {
								break;
							}
							up = board[up.getRow() - 1][column];
						}
					}					
				}
			} else {
				for (int i = line.length - 2; i >= 0; i--) {
					if (!empty.contains(line[i])) {
						Tile lower = board[line[i].getRow() + 1][column];
						int j = i;
						while (empty.contains(lower)) {
							lower.setNumber(line[j].getNumber());
							empty.remove(lower);
							line[j].setNumber(0);
							empty.add(line[j]);
							j++;
							moved = true;
							if (lower.getRow() == 3) {
								break;
							}
							lower = board[lower.getRow() + 1][column];
						}
					}
				}
			}

		} else {
			int row = line[0].getRow();
			if (direction.equals("left")) {
				for (int i = 1; i < line.length; i++) {
					if (!empty.contains(line[i])) {
						Tile right = board[row][line[i].getCol() - 1];
						int j = i;
						while (empty.contains(right)) {
							right.setNumber(line[j].getNumber());
							empty.remove(right);
							line[j].setNumber(0);
							empty.add(line[j]);
							j--;
							moved = true;
							if (right.getCol() == 0) {
								break;
							}
							right = board[row][right.getCol() - 1];
						}
					}
				}
			} else {
				for (int i = line.length - 2; i >= 0; i--) {
					if (!empty.contains(line[i])) {
						Tile right = board[row][line[i].getCol() + 1];
						int j = i;
						while (empty.contains(right)) {
							right.setNumber(line[j].getNumber());
							empty.remove(right);
							line[j].setNumber(0);
							empty.add(line[j]);
							j++;
							moved = true;
							if (right.getCol() == DIM - 1) {
								break;
							}
							right = board[row][right.getCol() + 1];
						}
					}
				}
			}
		}
		//System.out.println(Arrays.toString(line));
		return moved;
	}
	
	/**
	 * Make a tile either 2 or 4
	 */
	public void addTile() {

		if (empty.size() == 0) {
			// LOSE
			System.out.println("You Lost M8");
		} else {
			int index = r.nextInt(empty.size());
			Tile temp = empty.remove(index);
			if (r.nextInt(CHANCE_OF_4) == 0) {
				temp.setNumber(4);
			} else {
				temp.setNumber(2);
			}
		}
	}

	public void render(Graphics g) {
		for (Tile[] r : board) {
			for (Tile tile : r) {
				tile.render(g);
			}
		}
	}
	
	public void tick() {
		for (Tile[] r : board) {
			for (Tile tile : r) {
				tile.tick();
			}
		}
	}

}
