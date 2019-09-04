//Shivneel Chand 
//3-9-18
package textExcel;

public abstract class RealCell implements Cell {

	protected String value;

	public RealCell(String value) {
		this.value = value;
	}

	public abstract double getDoubleValue();

	public int compareTo(Object o) {
		if (o instanceof RealCell) {
			RealCell rc = (RealCell) o;
			if (this.getDoubleValue() > rc.getDoubleValue()) {
				return 1;
			} else if (this.getDoubleValue() == rc.getDoubleValue()) {
				return 0;
			} else {
				return -1;
			}
		}
		return 1;
	}
}
