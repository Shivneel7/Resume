//Shivneel Chand 
//3-9-18

package textExcel;

public class EmptyCell implements Cell {
	
	public EmptyCell() {
		
	}
	
	// text for spreadsheet cell display, must be exactly length 10
	public String abbreviatedCellText() {
		return "          ";
	}
	
	 // text for individual cell inspection, not truncated or padded
	public String fullCellText() {
		return "";
	}

	public int compareTo(Object o) {
		if(o instanceof EmptyCell) {
			return 0;
		}
		return -1;
	}
}
