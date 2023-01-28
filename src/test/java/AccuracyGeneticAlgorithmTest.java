import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import testfunctions.RosenbrockFunc;
import testfunctions.SphereFunc;

import java.util.ArrayList;

public class AccuracyGeneticAlgorithmTest
{
	@Test
	void GeneticalAlgorithmBuildsProperly()
	{
		GeneticAlgorithmParams algorithmParams = new GeneticAlgorithmParams();
		algorithmParams.populationSize = 600;
		algorithmParams.elitePercentage = 0.05;
		algorithmParams.mutationRate = 0.05;
		FunctionParams functionParams = new FunctionParams();
		functionParams.functionParamSize = 2;
		functionParams.lowerBound = -50;
		functionParams.upperBound = 50;
		functionParams.functionName = "Rosenbrock";

		GeneticAlgorithm algorithm = new GeneticAlgorithm(algorithmParams, functionParams);

		Assertions.assertNotEquals(algorithm, null);
	}

	@Test
	void GeneticalAlgorithmWithRosenbrockFunction()
	{
		ArrayList<Double> diffList = new ArrayList<>();
		ArrayList<Double> diffList1 = new ArrayList<>();
		ArrayList<Double> diffList2 = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			int variables = 5;
			Double upperBound = 5.12;
			Double lowerBound = -5.12;
			RosenbrockFunc func = new RosenbrockFunc(variables, lowerBound, upperBound, "Rosenbrock fun");

			GeneticAlgorithmParams algorithmParams = new GeneticAlgorithmParams();
			algorithmParams.populationSize = 400;
			algorithmParams.elitePercentage = 0.5;
			algorithmParams.mutationRate = 0.2;
			FunctionParams functionParams = new FunctionParams();
			functionParams.functionParamSize = variables;
			functionParams.lowerBound = lowerBound;
			functionParams.upperBound = upperBound;
			functionParams.functionName = "Rosenbrock fun";

			GeneticAlgorithm algorithm = new GeneticAlgorithm(algorithmParams, functionParams);
			algorithm.initializePopulation(func);
			algorithm.run(false);

			diffList.add( 0 - algorithm.getBestSolution().result );
			diffList1.add( 1 - algorithm.getBestSolution().variables.get(0));
			diffList2.add( 1 - algorithm.getBestSolution().variables.get(0));
			Assertions.assertNotEquals(algorithm, null);
		}

		for (int i = 0; i < diffList.size(); i++)
		{
			System.out.println("DIFFERENCES");
			System.out.println( "Result: " + diffList.get(i));
			System.out.println( "x: " + diffList1.get(i));
			System.out.println( "y: " + diffList2.get(i));

		}
	}

	@Test
	void GeneticalAlgorithmWithSphereFunction()
	{
		int variables = 2;
		Double upperBound = 5.12;
		Double lowerBound = -5.12;
		SphereFunc func = new SphereFunc(variables, lowerBound, upperBound, "Sphere fun");

		GeneticAlgorithmParams algorithmParams = new GeneticAlgorithmParams();
		algorithmParams.populationSize = 100;
		algorithmParams.elitePercentage = 0.5;
		algorithmParams.mutationRate = 0.2;
		FunctionParams functionParams = new FunctionParams();
		functionParams.functionParamSize = variables;
		functionParams.lowerBound = lowerBound;
		functionParams.upperBound = upperBound;
		functionParams.functionName = "Sphere fun";

		GeneticAlgorithm algorithm = new GeneticAlgorithm(algorithmParams, functionParams);
		algorithm.initializePopulation(func);
		algorithm.run(false);

		Assertions.assertNotEquals(algorithm, null);
	}

	@Test
	void GeneticalAlgorithmWithPolynomialFunction()
	{

	}


}
