import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import testfunctions.RosenbrockFunc;

public class GeneticAlgorithmTest
{
	@Test
	void GeneticalAlgorithmBuildsProperly()
	{
		int variables = 2;
		Double upperBound = 5.12;
		Double lowerBound = -5.12;
		RosenbrockFunc func = new RosenbrockFunc(2, lowerBound, upperBound);

		GeneticAlgorithmParams algorithmParams = new GeneticAlgorithmParams();
		algorithmParams.populationSize = 600;
		algorithmParams.elitePercentage = 0.05;
		algorithmParams.mutationRate = 0.05;
		FunctionParams functionParams = new FunctionParams();
		functionParams.functionParamSize = 2;
		functionParams.lowerBound = -50;
		functionParams.upperBound = 50;

		GeneticAlgorithm algorithm = new GeneticAlgorithm(algorithmParams, functionParams);
		algorithm.initializePopulation();

		Assertions.assertNotEquals(algorithm, null);
	}

	@Test
	void GeneticalAlgorithmWithRosenbrockFunction()
	{
		GeneticAlgorithmParams algorithmParams = new GeneticAlgorithmParams();
		algorithmParams.populationSize = 100;
		algorithmParams.elitePercentage = 0.5;
		algorithmParams.mutationRate = 0.2;
		FunctionParams functionParams = new FunctionParams();
		functionParams.functionParamSize = 2;
		functionParams.lowerBound = -5000000;
		functionParams.upperBound = 50000000;

		GeneticAlgorithm algorithm = new GeneticAlgorithm(algorithmParams, functionParams);
		algorithm.run();

		Assertions.assertNotEquals(algorithm, null);
	}

	@Test
	void GeneticalAlgorithmWithPolynomialFunction()
	{

	}


}
