//Shivneel Chand
//9-25-17
//This program will receive a quadratic formula from the user and will send the values to Quadratic class, then print
//the result that is returned from the quadrDescriber method.
import java.util.*;
public class QuadraticClient {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Welcome to the Quadratic Describer");
		System.out.println("Provide values for coefficients a, b, and c");
		do {
		System.out.println();
		System.out.print("a: ");
		double a = userInput.nextDouble();
		System.out.print("b: ");
		double b = userInput.nextDouble();
		System.out.print("c: ");
		double c = userInput.nextDouble();
		System.out.println();
		String result = Quadratic.quadrDescriber(a, b, c);
		System.out.println(result);
		System.out.println("Do you want to keep going? (Type \"quit\" to end)");
		}while(!(userInput.next().charAt(0) == 'q'));
	}

}
