package testfunctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RosenbrockFunc extends AbstractFunc
{
	public RosenbrockFunc(Integer numberOfVariables, Double lowerBound, Double upperBound, String name) {
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
	public RosenbrockFunc(RosenbrockFunc rosenbrockFunc)
	{
		this.variables = new ArrayList<>();
		this.bounds = new ArrayList<>();
		this.variables.addAll(rosenbrockFunc.variables);
		this.bounds.addAll(rosenbrockFunc.bounds);

	}


	@Override
	public Double evaluate() {
		int numberOfVariables = getNumberOfVariables() ;
		double sum = 0.0;

		for (int i = 0; i < numberOfVariables - 1; i++) {
			double temp1 = variables.get(i + 1) - (variables.get(i) * variables.get(i));
			double temp2 = 1.0 - variables.get(i);
			sum += (100.0 * temp1 * temp1) + (temp2 * temp2);
		}

		this.result = sum;
		return sum;
	}
	@Override
	public RosenbrockFunc cloneObject()
	{
		return new RosenbrockFunc(this);
	}
}
