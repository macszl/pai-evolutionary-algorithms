package testfunctions;

import util.Bounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractFunc
{
	public List<Double> variables;
	public List<Bounds> bounds;
	public String name;
	public Double result;

	public int getNumberOfVariables () {return bounds.size();}

	public void setBounds (List<Double> lowerBounds, List<Double> upperBounds)
	{
		bounds = new ArrayList<>();
		for (int i = 0; i < lowerBounds.size(); i++)
		{
			bounds.add(i, new Bounds(lowerBounds.get(i), upperBounds.get(i)));
		}
	}

	public void initialize (int variablesSize)
	{
		Random rand = new Random();
		for (int i = 0; i < variablesSize; i++)
		{
			variables.add(rand.nextDouble(bounds.get(i).getLowerBound(), bounds.get(i).getUpperBound()));
		}
	}

	public abstract Double evaluate ();

	//non-uniform mutation operator
	public void mutate ()
	{
		Random rand = new Random();
		for (int i = 0; i < variables.size(); i++)
		{
			//decent enough mutation
//			if( rand.nextDouble() < 0.5 )
//			{
//				double rnd = rand.nextDouble();
//				Double lowerBound = bounds.get(i).getLowerBound();
//				Double upperBound = bounds.get(i).getUpperBound();
//				double delta = (rnd * 2.0) - 1.0;
//				double newVar = delta * upperBound;
//
//				variables.set(i, newVar);
//			}
			if( rand.nextDouble() < 0.5 )
			{
				double rnd = rand.nextDouble();
				double delta = (rnd * 2.0) - 1.0;

				Double lowerBound = bounds.get(i).getLowerBound();
				Double upperBound = bounds.get(i).getUpperBound();
				double newVar = (upperBound / 10) * delta + variables.get(i);
				if( newVar >= upperBound || newVar <= lowerBound )
				{
					newVar = delta * upperBound;
				}
				variables.set(i, newVar);
			}


		}

	}

	public abstract AbstractFunc cloneObject ();
}
