package testfunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SphereFunc extends AbstractFunc
{
	public SphereFunc(Integer numberOfVariables, Double lowerBound, Double upperBound, String name) {
		List<Double> lowerLimit = new ArrayList<>(numberOfVariables) ;
		List<Double> upperLimit = new ArrayList<>(numberOfVariables) ;
		this.name = name;
		this.variables = new ArrayList<>(numberOfVariables);

		for (int i = 0; i < numberOfVariables; i++) {
			lowerLimit.add(lowerBound);
			upperLimit.add(upperBound);
		}
		setBounds(lowerLimit, upperLimit);
	}
	public SphereFunc(SphereFunc a)
	{
		this.variables = new ArrayList<>();
		this.bounds = new ArrayList<>();
		this.variables.addAll(a.variables);
		this.bounds.addAll(a.bounds);
	}

	@Override public Double evaluate ()
	{
		int numberOfVariables = getNumberOfVariables() ;
		double sum = 0.0;

		for (int i = 0; i < numberOfVariables; i++) {
			sum += variables.get(i) * variables.get(i);
		}

		return sum;
	}

	@Override
	public SphereFunc cloneObject()
	{
		return new SphereFunc(this);
	}
}
