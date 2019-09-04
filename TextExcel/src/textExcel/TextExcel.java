//Shivneel Chand 
//3-9-18

package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel {

	public static void main(String[] args) {
		
		Spreadsheet ss = new Spreadsheet();
		
		Scanner userInput = new Scanner(System.in);
		while (true) {// Accepts strings from the user until the user types "quit"
			System.out.print("enter command: ");
			String input = userInput.nextLine();
			if (input.equals("quit")) {
				break;
			}
			System.out.println(ss.processCommand(input));
		}
	}
}
