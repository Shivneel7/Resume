//Shivneel Chand 
//3-9-18
package textExcel;

public class PercentCell extends RealCell {
	
	public PercentCell(String input) {
		super(input);
	}

	public String abbreviatedCellText() {
		String temp = value;
		if(temp.contains(".")) {
			temp = value.split("\\.")[0] + "%";
		}
		for(int i = temp.length(); i < 10; i++) {
			temp += " ";
		}
		return temp.substring(0, 10);
	}

	public String fullCellText() {
		return ""+getDoubleValue();
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(value.replace("%", "")) * .01;
	}
}
