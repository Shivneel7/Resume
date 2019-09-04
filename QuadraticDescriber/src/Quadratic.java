//Shivneel Chand
//9-25-17
//This describes the quadratic graph to the user.
public class Quadratic {
	public static String quadrDescriber(double a, double b, double c) {
		String result = "Description of the graph of: \ny = " + a + " x^2 + " + b + " x + " + c + " \n";
		
		result += "\nOpens: ";
		if(a<0) {
			result += "Down \n";
		}else
			result += "Up \n";
		
		double aOS = round2(-1*b/(2*a));
		result +=  "Axis of Symmetry: " + aOS + "\n";
		result += "Vertex: (" + aOS + ", " + round2((a* aOS * aOS + b * aOS + c)) + ") \n";
		result += "x-intercept(s): " + quadForm(a,b,c) + "\n";
		result += "y-intercept: " + round2(c) + "\n";
		return result;
	}
	
	//calculates the discriminant
	public static double discriminant(double a, double b, double c) {
		return (b*b - (4*a*c));
	}
	//Takes two doubles and returns the lower double.
	public static double min(double numbah1, double numbah2) {
		if(numbah1 < numbah2) {
			return numbah1;
		}else {
			return numbah2;
		}
	}
	
	//Takes a double and returns the double rounded correctly to two decimal places
	public static double round2(double numbah) {
		int tempInt = (int) (numbah *1000);
		int roundNum = tempInt % 10;
		tempInt = tempInt / 10;
		if(roundNum >=5) {
			tempInt++;
		}else if(roundNum <= -5){
			tempInt--;
		}
		return tempInt/100.0;
	}
	//calculates roots of a quadratic equation
	public static String quadForm(double a, double b, double c) {
		double disc = discriminant(a,b,c);
		if(disc<0) {
			return "None";
		}
		if(disc == 0) {
			double root = (-1*b + sqrt(disc))/(2*a);
			return round2(root) + ""; //Converts root from a double to a String.
		}else {
			double root1 = round2((-1*b + sqrt(disc))/(2*a));
			double root2 = round2((-1*b - sqrt(disc))/(2*a));
			if(min(root1,root2) == root1) {
				return root1 + " and " + root2;
			}else {
				return root2 + " and " + root1;
			}
		}
	}
	//Returns the square root of a given double
	public static double sqrt(double numbah) {
		if(numbah < 0 ) {
			throw new IllegalArgumentException("Cannot find squareroot of negative number.");
		}
		if(numbah == 0) {//So that the program does not break from dividing by 0.
			return 0;
		}
		double A = numbah/2;
		double root = 0;
		while(root != (numbah / A + A)/2) {//This while loop will stop when the computer is not able to tell the difference 
			root = (numbah / A + A)/2;		//between this approximate root, and the previous approximation.
			A= root;
		}
		return round2(root);
	}
}
