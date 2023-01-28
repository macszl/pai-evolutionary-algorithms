import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import testfunctions.RosenbrockFunc;

import java.util.ArrayList;

public class PlotTest
{
	@Test void GeneticAlgorithmWithRosenbrockFunction ()
	{
		ArrayList<Double> bestResultList = new ArrayList<>();
		ArrayList<Double> bestXList = new ArrayList<>();
		ArrayList<Double> bestYList = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			int variables = 2;
			Double upperBound = 5.12;
			Double lowerBound = -5.12;
			RosenbrockFunc func = new RosenbrockFunc(variables, lowerBound, upperBound, "Rosenbrock fun");

			GeneticAlgorithmParams algorithmParams = new GeneticAlgorithmParams();
			algorithmParams.mutationRate = 0.2;
			algorithmParams.populationSize = 400;
			algorithmParams.elitePercentage = 0.5;
			FunctionParams functionParams = new FunctionParams();
			functionParams.functionParamSize = variables;
			functionParams.lowerBound = lowerBound;
			functionParams.upperBound = upperBound;
			functionParams.functionName = "Rosenbrock fun";

			GeneticAlgorithm algorithm = new GeneticAlgorithm(algorithmParams, functionParams);
			algorithm.initializePopulation(func);
			algorithm.run(true);

			bestResultList.add(algorithm.getBestSolution().result);
			bestXList.add(algorithm.getBestSolution().variables.get(0));
			bestYList.add(algorithm.getBestSolution().variables.get(0));
			Assertions.assertNotEquals(algorithm, null);
		}

		for (int i = 0; i < bestResultList.size(); i++)
		{
			System.out.println("DIFFERENCES");
			System.out.println("Result: " + bestResultList.get(i));
			System.out.println("x: " + bestXList.get(i));
			System.out.println("y: " + bestYList.get(i));

		}

		// Create Chart
		XYChart chart = new XYChartBuilder().width(600).height(500).title("Gaussian Blobs").xAxisTitle("X").yAxisTitle(
				"Y").build();

		// Customize Chart
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
		chart.getStyler().setChartTitleVisible(false);
		chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
		chart.getStyler().setMarkerSize(16);

		// Series
		chart.addSeries("Gaussian Blob 1", bestXList, bestYList);
		SwingWrapper<XYChart> chartDisplayer = new SwingWrapper<>(chart);
		chartDisplayer.displayChart();

	}


}
