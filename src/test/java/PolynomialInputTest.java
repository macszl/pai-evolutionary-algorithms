import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mariuszgromada.math.mxparser.mXparser;
import testfunctions.PolynomialFunc;

import java.util.ArrayList;

public class PolynomialInputTest
{
	@Test void GeneticalAlgorithmWithPolynomialFunction ()
	{
		mXparser.disableAlmostIntRounding();
		mXparser.disableUlpRounding();
		mXparser.setExactComparison();

		ArrayList<Double> diffList = new ArrayList<>();
		ArrayList<Double> diffList1 = new ArrayList<>();
		ArrayList<Double> diffList2 = new ArrayList<>();
		for (int i = 0; i < 2; i++)
		{
			int variables = 2;
			Double upperBound = 5.12;
			Double lowerBound = -5.12;

			String scannedMessage = "f(x) = x^2 + 6x + 9";

			PolynomialFunc func = new PolynomialFunc(scannedMessage, lowerBound, upperBound, "Polynomial fun");

			GeneticAlgorithmParams algorithmParams = new GeneticAlgorithmParams();
			algorithmParams.populationSize = 50;
			algorithmParams.elitePercentage = 0.5;
			algorithmParams.mutationRate = 0.2;
			algorithmParams.epochsCount = 2000;
			FunctionParams functionParams = new FunctionParams();
			functionParams.functionParamSize = func.getNumberOfVariables();
			functionParams.lowerBound = lowerBound;
			functionParams.upperBound = upperBound;
			functionParams.functionName = "Polynomial fun";

			GeneticAlgorithm algorithm = new GeneticAlgorithm(algorithmParams, functionParams);
			algorithm.initializePopulation(func);
			algorithm.run(false);

			diffList.add(algorithm.getBestSolution().result);
			diffList1.add(algorithm.getBestSolution().variables.get(0));
			diffList2.add(algorithm.getBestSolution().variables.get(0));
			Assertions.assertNotEquals(algorithm, null);
		}

		for (int i = 0; i < diffList.size(); i++)
		{
			System.out.println("DIFFERENCES");
			System.out.println("Result: " + diffList.get(i));
			System.out.println("x: " + diffList1.get(i));
			System.out.println("y: " + diffList2.get(i));

		}
	}
}
