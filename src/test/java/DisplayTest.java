import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import testfunctions.RosenbrockFunc;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DisplayTest
{
	private static final DecimalFormat df = new DecimalFormat("0.00000");

	public static void main (String[] args)
	{
		ArrayList<Double> bestResultList = new ArrayList<>();
		ArrayList<ArrayList<Double>> bestAxesList = new ArrayList<>();

		int variablesCount = 2;
		boolean geneticAlgorithmUsed = false;

		for (int i = 0; i < variablesCount; i++)
		{
			bestAxesList.add(new ArrayList<>());
		}
		String[] variableNameList = new String[]{"x", "y", "z", "w", "m"};


		for (int i = 0; i < 10; i++)
		{

			Double upperBound = 5.12;
			Double lowerBound = -5.12;
			RosenbrockFunc func = new RosenbrockFunc(variablesCount, lowerBound, upperBound, "Rosenbrock fun");

			SimulatedAnnealingAlgorithmParams annealingAlgorithmParams = new SimulatedAnnealingAlgorithmParams();
			GeneticAlgorithmParams geneticAlgorithmParams = new GeneticAlgorithmParams();
			annealingAlgorithmParams.startTemp = 10000;
			annealingAlgorithmParams.coolingRate = 0.1;
			annealingAlgorithmParams.endTemp = 0.1;
			annealingAlgorithmParams.iterationsCnt = 20000;

			geneticAlgorithmParams.epochsCount = 2000;
			geneticAlgorithmParams.populationSize = 100;
			geneticAlgorithmParams.elitePercentage = 0.2;
			geneticAlgorithmParams.mutationRate = 0.4;

			FunctionParams functionParams = new FunctionParams();
			functionParams.functionParamSize = variablesCount;
			functionParams.lowerBound = lowerBound;
			functionParams.upperBound = upperBound;
			functionParams.functionName = "Rosenbrock fun";

			if( !geneticAlgorithmUsed )
			{
				SimulatedAnnealingAlgorithm algorithm = new SimulatedAnnealingAlgorithm(annealingAlgorithmParams,
																						functionParams);
				algorithm.initialize(func);
				algorithm.run();

				bestResultList.add(algorithm.getBestSolution().result);
				for (int j = 0; j < variablesCount; j++)
				{
					bestAxesList.get(j).add(algorithm.getBestSolution().variables.get(j));
				}
			}
			else
			{
				GeneticAlgorithm algorithm = new GeneticAlgorithm(geneticAlgorithmParams, functionParams);
				algorithm.initializePopulation(func);
				algorithm.run(false);

				bestResultList.add(algorithm.getBestSolution().result);
				for (int j = 0; j < variablesCount; j++)
				{
					bestAxesList.get(j).add(algorithm.getBestSolution().variables.get(j));
				}
			}

		}

		for (int i = 0; i < bestResultList.size(); i++)
		{
			System.out.println("DIFFERENCES");
			System.out.println("Result: " + bestResultList.get(i));
			for (int j = 0; j < variablesCount; j++)
			{
				System.out.println(variableNameList[j] + ": " + bestAxesList.get(j).get(i));
			}
		}

		String title = "";
		if( geneticAlgorithmUsed )
		{
			title = "Genetic algorithm plot";
		}
		else
		{
			title = "Simulated annealing plot";
		}
		XYChart chart = new XYChartBuilder().width(600).height(500).title(title).xAxisTitle("X").yAxisTitle("Result")
											.build();

		chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
		chart.getStyler().setChartTitleVisible(false);
		chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
		chart.getStyler().setMarkerSize(10);

		chart.getStyler().setToolTipsEnabled(true);
		chart.getStyler().setToolTipFont(new Font("Verdana", Font.BOLD, 12));
		chart.getStyler().setToolTipHighlightColor(Color.CYAN);
		chart.getStyler().setToolTipBorderColor(Color.BLACK);
		chart.getStyler().setToolTipBackgroundColor(Color.LIGHT_GRAY);
		chart.getStyler().setToolTipType(Styler.ToolTipType.xAndYLabels);

		String seriesName = "";

		if( geneticAlgorithmUsed )
		{
			seriesName = "Genetic algorithm";
		}
		else
		{
			seriesName = "Simulated annealing algorithm";
		}
		XYSeries series = chart.addSeries(seriesName, bestAxesList.get(0), bestResultList);

		ArrayList<String> tooltipList = new ArrayList<>();

		for (int i = 0; i < bestResultList.size(); i++)
		{
			String tooltip = "";
			for (int j = 0; j < variablesCount; j++)
			{
				tooltip += variableNameList[j] + df.format(bestAxesList.get(j).get(i)) + " ";
			}
			tooltip += " Result: " + df.format(bestResultList.get(i));
			tooltipList.add(tooltip);
		}
		series.setToolTips(tooltipList.toArray(new String[0]));
		SwingWrapper<XYChart> chartDisplayer = new SwingWrapper<>(chart);
		chartDisplayer.displayChart();

	}
}
