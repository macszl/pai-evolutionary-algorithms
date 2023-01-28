import org.junit.jupiter.api.Test;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import testfunctions.RosenbrockFunc;

import java.util.ArrayList;

public class AverageValueTest
{
	@Test void GeneticAlgorithmWithRosenbrockFunction ()
	{
		ArrayList<Double> bestResultList = new ArrayList<>();
		ArrayList<Double> bestXList = new ArrayList<>();
		ArrayList<Double> bestYList = new ArrayList<>();
		ArrayList<Double> seriesX = new ArrayList<>();
		ArrayList<Double> seriesY = new ArrayList<>();

		GeneticAlgorithm algorithm;
		FunctionParams functionParams;
		GeneticAlgorithmParams algorithmParams;
		algorithmParams = new GeneticAlgorithmParams();
		algorithmParams.mutationRate = 0.2;
		algorithmParams.populationSize = 400;
		algorithmParams.elitePercentage = 0.5;

		for (int i = 0; i < 1; i++)
		{
			int variables = 2;
			Double upperBound = 5.12;
			Double lowerBound = -5.12;
			RosenbrockFunc func = new RosenbrockFunc(variables, lowerBound, upperBound, "Rosenbrock fun");

			algorithmParams = new GeneticAlgorithmParams();
			algorithmParams.mutationRate = 0.2;
			algorithmParams.populationSize = 400;
			algorithmParams.elitePercentage = 0.5;
			algorithmParams.epochsCount = 100;
			functionParams = new FunctionParams();
			functionParams.functionParamSize = variables;
			functionParams.lowerBound = lowerBound;
			functionParams.upperBound = upperBound;
			functionParams.functionName = "Rosenbrock fun";

			algorithm = new GeneticAlgorithm(algorithmParams, functionParams);
			algorithm.initializePopulation(func);
			algorithm.run(true);

			bestResultList.add(algorithm.getBestSolution().result);
			bestXList.add(algorithm.getBestSolution().variables.get(0));
			bestYList.add(algorithm.getBestSolution().variables.get(0));

			for (int j = 0; j < algorithmParams.epochsCount; j++)
			{
				seriesX.add((double) j);
				seriesY.add(algorithm.loggedAverages.get(j));
			}
		}

		for (int i = 0; i < bestResultList.size(); i++)
		{
			System.out.println("DIFFERENCES");
			System.out.println("Result: " + bestResultList.get(i));
			System.out.println("x: " + bestXList.get(i));
			System.out.println("y: " + bestYList.get(i));

		}

		// Create Chart
		XYChart chart = new XYChartBuilder().width(600).height(500).title("Averages of genetic algo").xAxisTitle("X")
											.yAxisTitle("Y").build();

		// Series
		chart.addSeries("Gaussian Blob 1", seriesX, seriesY);
		SwingWrapper<XYChart> chartDisplayer = new SwingWrapper<>(chart);
		chartDisplayer.displayChart();

	}

}


