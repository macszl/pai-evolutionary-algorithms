import testfunctions.AbstractFunc;
import testfunctions.RosenbrockFunc;
import util.ConstraintMethod;

import java.util.*;

import static util.ConstraintHelper.constraint;

public class GeneticAlgorithm
{
	private final int populationSize;
	// The rate at which genes mutate. Ranges from 0 to 1.
	private final double mutationRate;
	// How much percent of our top solutions are kept? Ranges from 0 to 1.
	private final int eliteIndividuals;
	private final int functionParamSize;
	private ArrayList<AbstractFunc> population;
	private final Comparator<AbstractFunc> comparator = Comparator.comparingDouble(AbstractFunc::evaluate);
	private final Random randomNumberGenerator;
	private final int tournamentSize;
	private final String functionName;
	public GeneticAlgorithm (GeneticAlgorithmParams algorithmParams, FunctionParams functionParams)
	{
		this.functionParamSize = functionParams.functionParamSize;
		this.populationSize = algorithmParams.populationSize;
		this.tournamentSize = (int) 20;
		this.eliteIndividuals = (int ) Math.ceil( (double) populationSize * algorithmParams.elitePercentage);
		this.mutationRate = algorithmParams.mutationRate;
		this.randomNumberGenerator = new Random();
		this.population = new ArrayList<>(populationSize);
		this.functionName = functionParams.functionName;
	}
	public AbstractFunc getBestSolution()
	{
		return population.get(0);
	}
	public void run()
	{
		for(int i = 0; i < 100; i++)
		{
			for(int j = eliteIndividuals; j < populationSize; j++)
			{
				ArrayList<AbstractFunc> matingPool = selection();
				ArrayList<AbstractFunc> offspring = reproduce(matingPool);
				replace(offspring);
			}
		}
	}
	public void initializePopulation(AbstractFunc func)
	{
		for (int i = 0; i < this.populationSize; i++)
		{
			population.add(func.cloneObject());
			population.get(i).initialize(functionParamSize);
		}

		//sort the population according to a comparator
		population.sort(comparator);
	}

	//selecting the 2 pop
	public ArrayList<AbstractFunc> selection()
	{
		ArrayList<AbstractFunc> bestSelectedPopulation = new ArrayList<>();
		//using the tournament method a couple times
		while (bestSelectedPopulation.size() < tournamentSize)
		{
			//tournament method to select the population
			ArrayList<AbstractFunc> selectedPopulation = new ArrayList<>();
			for (int j = 0; j < tournamentSize; j++)
			{
				int a = randomNumberGenerator.nextInt(0, population.size());
				selectedPopulation.add(population.get(a));
			}
			AbstractFunc bestPop = compare(selectedPopulation.get(0), selectedPopulation.get(1));
			if( !bestSelectedPopulation.contains(bestPop))
				bestSelectedPopulation.add(bestPop);
		}
		bestSelectedPopulation.sort(comparator);
		return bestSelectedPopulation;
	}

	//one point crossover method
	public ArrayList<AbstractFunc> crossover( AbstractFunc chromosome1, AbstractFunc chromosome2)
	{
		ArrayList<AbstractFunc> crossedOverList = new ArrayList<>();
		AbstractFunc offspring = chromosome1.cloneObject();
		AbstractFunc secondOffspring = chromosome2.cloneObject();

		if (randomNumberGenerator.nextDouble() <= 0.9) {
			double alpha = randomNumberGenerator.nextDouble();

			for (int i = 0; i < chromosome1.getNumberOfVariables(); i++) {
				double upperBound = chromosome1.bounds.get(i).getUpperBound();
				double lowerBound = chromosome1.bounds.get(i).getLowerBound();

				double valueX1 = alpha * chromosome1.variables.get(i) + (1.0 - alpha) * chromosome2.variables.get(i);
				double valueX2 = alpha * chromosome2.variables.get(i) + (1.0 - alpha) * chromosome1.variables.get(i);


				valueX1 = constraint(valueX1, lowerBound, upperBound, ConstraintMethod.BOUND);
				valueX2 = constraint(valueX2, lowerBound, upperBound, ConstraintMethod.BOUND);

				offspring.variables.set(i, valueX1);
				secondOffspring.variables.set(i, valueX2);
			}
		}


		Collections.addAll(crossedOverList, offspring, secondOffspring);
		return crossedOverList;
	}

	public void replace(ArrayList<AbstractFunc> offspringPopulation)
	{
		ArrayList<AbstractFunc> jointPopulation = new ArrayList<>();
		jointPopulation.addAll(population);
		jointPopulation.addAll(offspringPopulation);

		jointPopulation.sort(comparator);

		while (jointPopulation.size() > population.size()) {
			jointPopulation.remove(jointPopulation.size() - 1);
		}
		population = jointPopulation;
	}
	public ArrayList<AbstractFunc> reproduce( ArrayList<AbstractFunc> parents)
	{
		ArrayList<AbstractFunc> offspring = new ArrayList<>();
		int size = parents.size();
		while (offspring.size() < parents.size())
		{
			AbstractFunc parent1 = parents.get(randomNumberGenerator.nextInt(0, size));
			AbstractFunc parent2 =	parents.get(randomNumberGenerator.nextInt(0, size));
			if(parent1 != parent2)
			{
				ArrayList<AbstractFunc> crossoverResult = crossover(parent1, parent2);
				for (int i = 0; i < crossoverResult.size(); i++)
				{
					if(randomNumberGenerator.nextDouble() <= mutationRate)
					{
						crossoverResult.get(i).mutate();
					}
				}
				offspring.addAll(crossoverResult);
				offspring.sort(comparator);
			}
		}
		return offspring;
	}

	public AbstractFunc compare(AbstractFunc pop, AbstractFunc secondPop)
	{
		int diff = comparator.compare(pop, secondPop);
		if(diff >= 0)
			return pop;
		else
			return secondPop;
	}

}