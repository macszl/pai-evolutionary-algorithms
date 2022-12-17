package testfunctions;

import util.Bounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractFunc
{
	public List<Double> variables;
	public List<Bounds> bounds;
	public String name;

	public int getNumberOfVariables() { return bounds.size() ;}

	public void setBounds (List<Double> lowerBounds, List<Double> upperBounds)
	{
		bounds = new ArrayList<>();
		for (int i = 0; i < lowerBounds.size(); i++)
		{
			bounds.add(i, new Bounds(lowerBounds.get(i), upperBounds.get(i)));
		}
	}

	public void initialize(int variablesSize)
	{
		Random rand = new Random();
		for (int i = 0; i < variablesSize; i++)
		{
			variables.add( rand.nextDouble(bounds.get(i).getLowerBound(), bounds.get(i).getUpperBound()));
		}
	}

	public abstract Double evaluate();
	public void mutate()
	{
		Random rand = new Random();
		int mutated_idx = rand.nextInt(0, getNumberOfVariables());
		double elem = variables.get(mutated_idx);
		double mult = rand.nextDouble(-0.1,0.1);
		if(elem * mult < bounds.get(mutated_idx).getLowerBound() )
		{
			variables.set(mutated_idx, elem * mult);
		}
		if(elem * mult < bounds.get(mutated_idx).getUpperBound())
		{
			variables.set(mutated_idx, elem * mult);
		}
	}

	public abstract AbstractFunc cloneObject();
}
