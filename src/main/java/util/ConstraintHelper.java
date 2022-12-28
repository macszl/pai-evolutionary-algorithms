package util;

import java.util.Random;

import static util.ConstraintHelper.constraintBound;

public class ConstraintHelper
{
	public static Double constraintOppositeBound(double value, double lowerBound, double upperBound)
	{
		double result = value ;
		if (value < lowerBound) {
			result = upperBound ;
		}
		if (value > upperBound) {
			result = lowerBound ;
		}

		return result ;
	}
	public static Double constraintBound(double value, double lowerBound, double upperBound)
	{
		double result = value ;
		if (value < lowerBound) {
			result = lowerBound ;
		}
		if (value > upperBound) {
			result = upperBound ;
		}

		return result ;
	}
	public static Double constraintRandom(double value, double lowerBound, double upperBound)
	{
		Random randomGenerator = new Random();
		double result = value ;
		if (value < lowerBound) {
			result = randomGenerator.nextDouble(lowerBound, upperBound) ;
		}
		if (value > upperBound) {
			result = randomGenerator.nextDouble(lowerBound, upperBound) ;
		}

		return result ;
	}
	public static Double constraint(double valueX1, double lowerBound, double upperBound, ConstraintMethod method)
	{
		if(method == ConstraintMethod.BOUND)
		{
			return constraintBound(valueX1, lowerBound, upperBound);
		}
		else if( method == ConstraintMethod.RANDOM)
		{
			return constraintRandom(valueX1, lowerBound, upperBound);
		}
		else {
			return constraintOppositeBound(valueX1, lowerBound, upperBound);
		}
	}
}
