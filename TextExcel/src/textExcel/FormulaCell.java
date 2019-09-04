//Shivneel Chand 
//3-9-18
package textExcel;

import java.util.ArrayList;

public class FormulaCell extends RealCell {

	private Spreadsheet ss;

	public FormulaCell(String value, Spreadsheet ss) {
		super(value);
		this.ss = ss;
	}

	public String abbreviatedCellText() {
		String temp = "" + getDoubleValue();
		for (int i = temp.length(); i < 10; i++) {
			temp += " ";
		}
		return temp.substring(0, 10);
	}

	public String fullCellText() {
		return value;
	}

	public double getDoubleValue() {
		String temp = value.replace("( ", "").toUpperCase();
		String[] parsedValues = temp.split(" ");

		double answer = 0;

		if (parsedValues[0].equals("AVG")) {// Average
			answer = sum(parsedValues[1]) / ss.getCells(parsedValues[1]).size();

		} else if (parsedValues[0].equals("SUM")) {// Addition
			answer = sum(parsedValues[1]);

		} else {
			// Normal Formula Cell with doubles and cells
			answer = stringToDouble(parsedValues[0]);

			for (int i = 2; i < parsedValues.length; i += 2) {
				String operator = parsedValues[i - 1];
				double operand = stringToDouble(parsedValues[i]);

				if (operator.equals("+")) {
					answer += operand;
				} else if (operator.equals("-")) {
					answer -= operand;
				} else if (operator.equals("*")) {
					answer *= operand;
				} else if (operator.equals("/")) {
					answer /= operand;
				}
			}
		}
		return answer;
	}

	/**
	 * @param s - String that will turn into a double
	 * @return The double value either from a Cell, or from parsing the String
	 */
	public double stringToDouble(String s) {
		if (Character.isDigit(s.charAt(0)) || s.charAt(0) == '-') {
			return Double.parseDouble(s);
		}
		return ((RealCell) ss.getCell(new SpreadsheetLocation(s))).getDoubleValue();
	}

	public double sum(String cellRange) {
		ArrayList<Cell> cells = ss.getCells(cellRange);
		double sum = 0;
		for (Cell c : cells) {
			sum += ((RealCell) c).getDoubleValue();
		}
		return sum;
	}

}
