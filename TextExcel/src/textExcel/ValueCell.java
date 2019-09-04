//Shivneel Chand 
//3-9-18
package textExcel;

public class ValueCell extends RealCell {
	
	public ValueCell(String value) {
		super(value);
	}
	
	public String abbreviatedCellText() {
		String temp = "" + getDoubleValue();
		for(int i = temp.length(); i < 10; i++) {
			temp += " ";
		}
		return temp.substring(0, 10);
	}
	
	public String fullCellText() {
		return value;
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(value);
	}
}
