//Shivneel Chand
//12-13-2017
//This class takes continuous user input from the console of a fractional equation 
//and prints the simplified fractional form of the equation (w/objects)

package fracCalc;

import java.util.Scanner;

public class FracCalc {

    public static void main(String[] args) {
    	Scanner userInput = new Scanner(System.in);
    	while(true) {//Accepts strings from the user until the user types "quit"
    		System.out.println("Type a fractional expression to evaluate it or type \"quit\" to terminate the program.");
    		String input = userInput.nextLine();
    		if(input.equals("quit")) {
    			break;
    		}
    		System.out.println(produceAnswer(input));
    	}
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input) {
    	String[] parsedInput = input.split(" ");
    	//if the input has an even number of parts, then the input was not in the proper format.
    	if(parsedInput.length % 2 == 0 || parsedInput.length == 1) {
    		return "ERROR: Please check you expression and try again.";
    	}
    	String resultString = "";
        
    	Fraction operand1 = new Fraction(parsedInput[0]); 
    	//for multiple operands, I save a copy of result in operand1, so operand 1 is created outside the loop
    	Fraction operand2;
    	
    	Fraction result;
    	
    	for(int i = 2; i < parsedInput.length; i += 2) {
    		operand2 = new Fraction(parsedInput[i]);
	        String operator = parsedInput[i-1];
	        
	        if(operand1.isDenominatorZero() || operand2.isDenominatorZero()) {//checks if denominator of any operands is 0, so we don't try and divide by 0.
	        	return "ERROR: Please stop trying to divide by 0.";
	        }
	        if(operator.equals("+")){
	        	result = operand1.add(operand2);
	        	
	        }else if(operator.equals("-")) {
	        	operand2.changeSign();
	        	result = operand1.add(operand2);
	        	
	        }else if(operator.equals("*")) {
	        	result = operand1.multiply(operand2);

	        }else if(operator.equals("/")){  	
	        	operand2.reciprocate();
	        	if(operand2.isDenominatorZero()) {//once we reciprocate, we have to recheck in case the number is 0.
	        		return "ERROR: Please stop trying to divide by 0.";
	        	}
	        	result = operand1.multiply(operand2);

	        }else {
	        	return "ERROR: Please try a different operator.";
	        }
	        operand1 = new Fraction(result);//can't "operand1 = result" b/c then operand1 is the same as result instead of a copy of result.
	        result.simplify();
	        resultString = result.toString();
    	}
		return resultString;
    }
}
