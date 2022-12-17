import testfunctions.AbstractFunc;
import testfunctions.RosenbrockFunc;

import java.util.*;

public class GeneticAlgorithm
{
	private final int populationSize;
	// The rate at which genes mutate. Ranges from 0 to 1.
	private final double mutationRate;
	// How much percent of our top solutions are kept? Ranges from 0 to 1.
	private final int eliteIndividuals;

	private final double upperBound;
	private final double lowerBound;
	private final int functionParamSize;
	private ArrayList<AbstractFunc> population;
	private final Comparator<AbstractFunc> comparator = Comparator.comparingDouble(AbstractFunc::evaluate);
	private final Random randomNumberGenerator;
	private final int tournamentSize;
	public GeneticAlgorithm (GeneticAlgorithmParams algorithmParams, FunctionParams functionParams)
	{
		this.upperBound = functionParams.upperBound;
		this.lowerBound = functionParams.lowerBound;
		this.functionParamSize = functionParams.functionParamSize;
		this.populationSize = algorithmParams.populationSize;
		this.tournamentSize = (int) Math.round(populationSize * 0.15);
		this.eliteIndividuals = (int ) Math.ceil( (double) populationSize * algorithmParams.elitePercentage);
		this.mutationRate = algorithmParams.mutationRate;
		this.randomNumberGenerator = new Random();
		this.population = new ArrayList<>(populationSize);
	}
	public void run()
	{
		initializePopulation();
		for(int i = 0; i < 5000; i++)
		{
			ArrayList<AbstractFunc> matingPool = selection();
			ArrayList<AbstractFunc> offspring = reproduce(matingPool);
			replace(offspring);

		}
		System.out.println("x: " + this.population.get(0).variables.get(0));
		System.out.println("y: " + this.population.get(0).variables.get(1));
		System.out.println("Value: " + this.population.get(0).evaluate());

		System.out.println("x: " + this.population.get(1).variables.get(0));
		System.out.println("y: " + this.population.get(1).variables.get(1));
		System.out.println("Value: " + this.population.get(1).evaluate());
	}
	public void initializePopulation()
	{
		for (int i = 0; i < this.populationSize; i++)
		{
			population.add(new RosenbrockFunc(functionParamSize, lowerBound,upperBound));
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
		for(int i = 0; i < 4; i++)
		{
			//tournament method to select the population
			ArrayList<AbstractFunc> selectedPopulation = new ArrayList<>();
			for (int j = 0; j < tournamentSize; j++)
			{
				int a = randomNumberGenerator.nextInt(eliteIndividuals, population.size());
				selectedPopulation.add(population.get(a));
			}
			AbstractFunc bestPop = compare(selectedPopulation.get(0), selectedPopulation.get(1));
			bestSelectedPopulation.add(bestPop);
		}
		bestSelectedPopulation.sort(comparator);
		return bestSelectedPopulation;
	}

	//one point crossover method
	public ArrayList<AbstractFunc> crossover( AbstractFunc chromosome1, AbstractFunc chromosome2)
	{
		int crossoverPoint = randomNumberGenerator.nextInt(0, chromosome1.getNumberOfVariables());
		ArrayList<AbstractFunc> crossedOverList = new ArrayList<>();
		AbstractFunc offspring = chromosome1.cloneObject();
		AbstractFunc secondOffspring = chromosome2.cloneObject();

		for (int i = 0; i < crossoverPoint; i++)
		{
			offspring.variables.set(i, chromosome1.variables.get(i));
			secondOffspring.variables.set(i, chromosome2.variables.get(i));
		}
		for (int i = crossoverPoint; i < offspring.getNumberOfVariables(); i++)
		{
			offspring.variables.set(i, chromosome2.variables.get(i));
			secondOffspring.variables.set(i, chromosome1.variables.get(i));
		}
		Collections.addAll(crossedOverList, offspring, secondOffspring);
		return crossedOverList;
	}

	public AbstractFunc mutation(AbstractFunc pop)
	{
		if( randomNumberGenerator.nextDouble() < mutationRate)
		{
			pop.mutate();
		}
		return pop;
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
		ArrayList<AbstractFunc> offspring = crossover(parents.get(0), parents.get(1));
		ArrayList<AbstractFunc> firstOffspring = new ArrayList<>();
		firstOffspring.add(offspring.get(0));
		firstOffspring.set(0, mutation(firstOffspring.get(0)));
		return firstOffspring;
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