//Shivneel Chand 
//3-9-18

package textExcel;

import java.util.ArrayList;

public class Spreadsheet implements Grid {

	private Cell[][] cells;

	public Spreadsheet() {
		cells = new Cell[getRows()][getCols()];
		clear();
	}

	public void clear() {
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getCols(); j++) {
				cells[i][j] = new EmptyCell();
			}
		}
	}

	public String processCommand(String command) {

		if (command.contains("=")) {

			String[] arr = command.split(" = ", 2);
			SpreadsheetLocation l = new SpreadsheetLocation(arr[0]);

			if (arr[1].startsWith("\"")) {// Makes TextCell by checking for quotes
				cells[l.getRow()][l.getCol()] = new TextCell(arr[1].replace("\"", ""));

			} else if (arr[1].startsWith("(")) {// Makes FormulaCell by looking for parentheses
				cells[l.getRow()][l.getCol()] = new FormulaCell(arr[1], this);

			} else if (arr[1].endsWith("%")) {// Makes percent cell by looking for percent
				cells[l.getRow()][l.getCol()] = new PercentCell(arr[1]);
			} else {
				cells[l.getRow()][l.getCol()] = new ValueCell(arr[1]);
			}

			return getGridText();

		} else if (command.length() == 2 || command.length() == 3) {
			return getCell(new SpreadsheetLocation(command)).fullCellText();
		}

		command = command.toUpperCase();
		if (command.equals("CLEAR")) {
			clear();
			return getGridText();

		} else if (command.startsWith("CLEAR ")) {
			SpreadsheetLocation l = new SpreadsheetLocation(command.substring(6));
			cells[l.getRow()][l.getCol()] = new EmptyCell();
			return getGridText();

		} else if (command.startsWith("SORTA")) {
			// I did not do checkpoint 6
			putBack(command.substring(6), getSortedArrayList(command.substring(6)), false);
			return getGridText();

		} else if (command.startsWith("SORTD")) {
			putBack(command.substring(6), getSortedArrayList(command.substring(6)), true);
			return getGridText();
		}
		return "";

	}

	/**
	 * 
	 * @param cellRange
	 *            - String that represents the cell Range in the format<br>
	 *            top left corner-bottom right corner. ex) a1-c3
	 * @return a sorted ArrayList<Cell> of the cells from the cellRange
	 */
	public ArrayList<Cell> getSortedArrayList(String cellRange) {
		ArrayList<Cell> list = getCells(cellRange);

		for (int i = 1; i < list.size(); i++) {
			Cell temp = list.get(i);//cell we are inserting
			int j = i - 1;//index of the cell we are comparing to the cell we are inserting.
			while (j >= 0 && temp.compareTo(list.get(j)) < 0) {
				j--;
			}
			list.add(j + 1, list.remove(i));
		}
		
		return list;
	}

	/**
	 * 
	 * @param cellRange
	 *            - String that represents the cell Range in the format<br>
	 *            top left corner-bottom right corner. ex) a1-c3
	 * @param list
	 *            -List containing the Cells that will be put in the CellRange
	 * @param reversed
	 *            -Whether to put the list back in reversed order or not.
	 */
	private void putBack(String cellRange, ArrayList<Cell> list, boolean reversed) {
		int indexOfDash = cellRange.indexOf('-');
		SpreadsheetLocation firstCell = new SpreadsheetLocation(cellRange.substring(0, indexOfDash));
		SpreadsheetLocation secondCell = new SpreadsheetLocation(cellRange.substring(indexOfDash + 1, cellRange.length()));
		
		int i = 0;
		if (reversed) {
			i = list.size() - 1; //If sortd, this will put the cells in the list backwards.
		}
		for (int row = firstCell.getRow(); row <= secondCell.getRow(); row++) {
			for (int col = firstCell.getCol(); col <= secondCell.getCol(); col++) {
				cells[row][col] = list.get(i);
				if (reversed) {
					i--;//so that cells will be put back backwards if using sortd
				} else
					i++;
			}
		}
	}

	public int getRows() {
		return 20;
	}

	public int getCols() {
		return 12;
	}

	public Cell getCell(Location loc) {
		return cells[loc.getRow()][loc.getCol()];
	}

	public Cell getCell(int row, int col) {
		return cells[row][col];
	}

	public String getGridText() {
		String fullSheet = "   ";
		for (int i = 0; i < getCols(); i++) {
			fullSheet += ("|" + (char) (i + 'A') + "         ");
		}
		fullSheet += "|\n";

		for (int i = 1; i <= getRows(); i++) {
			if (i < 10) {
				fullSheet += i + "  ";// for two digit numbers
			} else {
				fullSheet += i + " ";// for one digit numbers
			}

			for (int j = 0; j < getCols(); j++) {
				fullSheet += "|" + cells[i - 1][j].abbreviatedCellText();
			}
			fullSheet += "|\n";
		}
		return fullSheet;
	}

	/**
	 * @param cellRange
	 *            - String that represents the cell Range in the format:<br>
	 *            top left corner-bottom right corner. ex) a1-c3
	 * 
	 * @return an ArrayList of RealCells that contains all cells in the Range
	 */
	public ArrayList<Cell> getCells(String cellRange) {
		// Was going to use this for the sorting but did not do checkpoint 6,
		// So, I used this method for the formula cell math
		ArrayList<Cell> cells = new ArrayList<Cell>();

		int index = cellRange.indexOf('-');
		SpreadsheetLocation firstCell = new SpreadsheetLocation(cellRange.substring(0, index));
		SpreadsheetLocation secondCell = new SpreadsheetLocation(cellRange.substring(index + 1, cellRange.length()));

		for (int row = firstCell.getRow(); row <= secondCell.getRow(); row++) {
			for (int col = firstCell.getCol(); col <= secondCell.getCol(); col++) {
				cells.add(getCell(row, col));
			}
		}
		return cells;
	}
}
