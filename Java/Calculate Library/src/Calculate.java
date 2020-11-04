//Shivneel Chand
//October 31 2017
//My self written math library 
//It contains a series of methods to do basic math functions.
public class Calculate {	
	//returns the square of the given int
	public static int square(int num) {
		return num * num;
	}
	// returns the cube of the given int 
	public static int cube(int num) {
		return num * num * num;
	}
	//returns the average of two doubles
	public static double average(double num1, double num2) {
		return (num1 + num2)/2;
	}
	//returns the average of three doubles
	public static double average(double num1, double num2, double num3) {
		return (num1 + num2 + num3) / 3;
	}
	//returns the degree form of a given radian
	public static double toDegrees(double num) {
		return (num/3.14159 * 180);
	}
	//returns the radian form of a given double
	public static double toRadians(double num) {
		return ((num *3.14159) / 180);
	}
	//returns the discriminant of a quadratic equation given the coefficients a, b, and c
	public static double discriminant(double a, double b, double c) {
		return (b*b - (4*a*c));
	}
	//returns the Improper fraction form of a given mixed number
	public static String toImproperFrac(int wholeNumber, int numerator, int denominator) {
		int newNumerator = wholeNumber * denominator + numerator;
		return (newNumerator + "/" + denominator);
	}
	//returns the mixed number form of a given improper fraction
	public static String toMixedNum(int numerator, int denominator) {
		int wholeNumber = numerator/denominator;
		int newNumerator = numerator%denominator;
		return (wholeNumber + "_" + newNumerator + "/" + denominator);
	}
	//accepts the coefficients  of a binomial, and multiplies them into a quadratic equation
	public static String foil(int a, int b, int c, int d, String variable) {
		int middleNumber = b*c + a* d;
		return (a*c + variable + "^2 + " + middleNumber + variable + " + " + b*d);
	}
	// accepts two integers and returns true if the first number is evenly divisible by the second, and false if there is a remainder.
	public static boolean isDivisibleBy(int numerator, int denominator) {
		if(denominator == 0) {
			throw new IllegalArgumentException("Cannot divide by 0.");
		}
		if(numerator % denominator == 0) {
			return true;
		}else {
			return false;
		}
	}
	//returns the absolute value of a number.
	public static double absValue(double number) {
		if(number < 0) {
			return -1*number;
		}else{
			return number;
		}
	}
	//takes two doubles and returns the larger value
	public static double max(double num1, double num2) {
		if(num1<num2) {
			return num2;
		}else {
			return num1;
		}
	}
	//takes three doubles, and returns the largest value
	public static double max(double num1, double num2, double num3) {
		if(num1>num2 && num1>num3) {
			return num1;
		}else if(num2>num1 && num2>num3){
			return num2;
		}else {
			return num3;
		}
	}
	//takes two integers and returns the lower integer
	public static int min(int num1, int num2) {
		if(num1 < num2) {
			return num1;
		}else {
			return num2;
		}
	}
	//Takes two doubles and returns the lower double.
	public static double min(double num1, double num2) {
		if(num1 < num2) {
			return num1;
		}else {
			return num2;
		}
	}
	//Takes a double and returns the double rounded correctly to two decimal places
	public static double round2(double num) {
		int tempInt = (int) (num *1000);
		int roundNum = tempInt % 10;
		tempInt = tempInt / 10;
		if(roundNum >=5) {
			tempInt++;
		}else if(roundNum <= -5){
			tempInt--;
		}
		return tempInt/100.0;
	}
	//Takes one double and one positive integer, and raises the double to the power of the integer.
	public static double exponent(double base, int exponent) {
		if(exponent < 0) {
			throw new IllegalArgumentException("Cannot find a number raised to a negative value.");
		}
		double result = base;
		if(exponent == 0) {
			return 1;
		}
		for(int i = 1; i < exponent; i++) {
			result *= base;
		}
		return result;
	}
	//Returns the factorial of the value passed
	public static int factorial(int num) {
		if(num<0) {
			throw new IllegalArgumentException("Cannot calculate factorial of negative number!");
		}
		int result = 1;
		for(int i = 1; i <= num; i++) {
			result*= i;
		}
		return result;
	}
	//returns true or false based on whether the number given is prime or not.
	public static boolean isPrime(int num) {
		if(num <2) {
			return false;
		}
		int numOfFactors = 0;
		for(int i = 2; i <= num; i++) {
			boolean result = isDivisibleBy(num,i);
			if(result == true) {
				numOfFactors++;
			}
		}
		if(numOfFactors>1) {
			return false;
		}else {
			return true;
		}
	}
	//This method takes two integers and returns the greatest common factor of the two integers
	public static int gcf(int num1, int num2) {
		int factor;
		for(factor = num1; !(isDivisibleBy(num1,factor)&&(isDivisibleBy(num2,factor))) ; factor--) {
		}
		return factor;
	}
	//This method returns an approximation of the square root of a given double.
	public static double sqrt(double num) {
		if(num < 0 ) {
			throw new IllegalArgumentException("Cannot find squareroot of negative number.");
		}
		if(num == 0) {//So that the program does not break from dividing by 0.
			return 0;
		}
		double A = num/2;
		double root = 0;
		while(root != (num / A + A)/2) {//This while loop will stop when the computer is not able to tell the difference 
			root = (num / A + A)/2;		//between this approximate root, and the previous approximation.
			A= root;
		}
		return round2(root);
	}
	//This method returns the roots of a quadratic equation given the coefficients of the quadratic equation.
	public static String quadForm(int a, int b, int c) {
		double disc = discriminant(a,b,c);
		if(disc<0) {
			return "no real roots";
		}
		if(disc == 0) {
			double root = (-1*b + sqrt(disc))/2*a;
			return round2(root) + ""; //Converts root from a double to a String.
		}else {
			double root1 = round2((-1*b + sqrt(disc))/2*a);
			double root2 = round2((-1*b - sqrt(disc))/2*a);
			if(min(root1,root2) == root1) {
				return root1 + " and " + root2;
			}else {
				return root2 + " and " + root1;
			}
		}
	}
}