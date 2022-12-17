package util;

public class Bounds
{
	private double lowerBound;
	private double upperBound;


	public Bounds (double lowerBound, double upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public double getLowerBound ()
	{
		return lowerBound;
	}

	public double getUpperBound ()
	{
		return upperBound;
	}
}
