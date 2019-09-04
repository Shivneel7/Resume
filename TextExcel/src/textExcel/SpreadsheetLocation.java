//Shivneel Chand 
//3-9-18

package textExcel;

public class SpreadsheetLocation implements Location{
	
	private String cellName;
	
	public SpreadsheetLocation(String cellName){
		cellName = cellName.toUpperCase();
    	this.cellName = cellName;
    }
    
    public int getRow(){
        return Integer.parseInt(cellName.substring(1, cellName.length()))-1;
    }

    public int getCol(){
        return cellName.charAt(0)-'A';
        		
    }
    
}
