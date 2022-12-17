import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import testfunctions.RosenbrockFunc;
import testfunctions.SphereFunc;

public class GeneticAlgorithmTest
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
		int variables = 2;
		Double upperBound = 5.12;
		Double lowerBound = -5.12;
		RosenbrockFunc func = new RosenbrockFunc(variables, lowerBound, upperBound, "Rosenbrock fun");

		GeneticAlgorithmParams algorithmParams = new GeneticAlgorithmParams();
		algorithmParams.populationSize = 100;
		algorithmParams.elitePercentage = 0.5;
		algorithmParams.mutationRate = 0.2;
		FunctionParams functionParams = new FunctionParams();
		functionParams.functionParamSize = variables;
		functionParams.lowerBound = lowerBound;
		functionParams.upperBound = upperBound;
		functionParams.functionName = "Rosenbrock fun";

		GeneticAlgorithm algorithm = new GeneticAlgorithm(algorithmParams, functionParams);
		algorithm.initializePopulation(func);
		algorithm.run();

		Assertions.assertNotEquals(algorithm, null);
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
		algorithm.run();

		Assertions.assertNotEquals(algorithm, null);
	}

	@Test
	void GeneticalAlgorithmWithPolynomialFunction()
	{

	}


}
