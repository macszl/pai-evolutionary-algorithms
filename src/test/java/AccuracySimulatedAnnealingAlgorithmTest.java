import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testfunctions.RosenbrockFunc;
import testfunctions.SphereFunc;

import java.util.ArrayList;

public class AccuracySimulatedAnnealingAlgorithmTest
{
	@Test
	void SimulatedAnnealingAlgorithmBuildsProperly()
	{
		SimulatedAnnealingAlgorithmParams algorithmParams = new SimulatedAnnealingAlgorithmParams();
		algorithmParams.startTemp = 10000;
		algorithmParams.coolingRate = 0.1;
		algorithmParams.endTemp = 0.1;
		algorithmParams.iterationsCnt = 20000;
		FunctionParams functionParams = new FunctionParams();
		functionParams.functionParamSize = 2;
		functionParams.lowerBound = -50;
		functionParams.upperBound = 50;
		functionParams.functionName = "Rosenbrock";

		SimulatedAnnealingAlgorithm algorithm = new SimulatedAnnealingAlgorithm(algorithmParams, functionParams);

		Assertions.assertNotEquals(algorithm, null);
	}

	@Test
	void SimulatedAnnealingAlgorithmWithRosenbrockFunction()
	{
		ArrayList<Double> diffList = new ArrayList<>();
		ArrayList<Double> diffList1 = new ArrayList<>();
		ArrayList<Double> diffList2 = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			int variables = 2;
			Double upperBound = 5.12;
			Double lowerBound = -5.12;
			RosenbrockFunc func = new RosenbrockFunc(variables, lowerBound, upperBound, "Rosenbrock fun");

			SimulatedAnnealingAlgorithmParams algorithmParams = new SimulatedAnnealingAlgorithmParams();
			algorithmParams.startTemp = 10000;
			algorithmParams.coolingRate = 0.1;
			algorithmParams.endTemp = 0.1;
			algorithmParams.iterationsCnt = 20000;
			FunctionParams functionParams = new FunctionParams();
			functionParams.functionParamSize = variables;
			functionParams.lowerBound = lowerBound;
			functionParams.upperBound = upperBound;
			functionParams.functionName = "Rosenbrock fun";

			SimulatedAnnealingAlgorithm algorithm = new SimulatedAnnealingAlgorithm(algorithmParams, functionParams);
			algorithm.initialize(func);
			algorithm.run();

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
	void SimulatedAnnealingAlgorithmWithSphereFunction()
	{
		int variables = 2;
		Double upperBound = 5.12;
		Double lowerBound = -5.12;
		SphereFunc func = new SphereFunc(variables, lowerBound, upperBound, "Sphere fun");

		SimulatedAnnealingAlgorithmParams algorithmParams = new SimulatedAnnealingAlgorithmParams();
		algorithmParams.startTemp = 10000;
		algorithmParams.coolingRate = 0.1;
		algorithmParams.endTemp = 0.1;
		algorithmParams.iterationsCnt = 20000;
		FunctionParams functionParams = new FunctionParams();
		functionParams.functionParamSize = variables;
		functionParams.lowerBound = lowerBound;
		functionParams.upperBound = upperBound;
		functionParams.functionName = "Sphere fun";

		SimulatedAnnealingAlgorithm algorithm = new SimulatedAnnealingAlgorithm(algorithmParams, functionParams);
		algorithm.initialize(func);
		algorithm.run();

		Assertions.assertNotEquals(algorithm, null);
	}

	@Test
	void SimulatedAnnealingAlgorithmWithPolynomialFunction()
	{

	}
}
