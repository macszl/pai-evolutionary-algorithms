import testfunctions.AbstractFunc;

import java.util.Random;

public class SimulatedAnnealingAlgorithm
{
	private final int iterationsCnt;

	private final double startTemp ;

	private final double coolingRate;

	private final double endTemp;

	private AbstractFunc solution;
	private AbstractFunc bestSolution;
	private final Random randomNumberGenerator;
	private final int functionParamSize;
	public SimulatedAnnealingAlgorithm(SimulatedAnnealingAlgorithmParams algorithmParams, FunctionParams functionParams)
	{
		this.iterationsCnt = algorithmParams.iterationsCnt;
		this.startTemp = algorithmParams.startTemp;
		this.coolingRate = algorithmParams.coolingRate;
		this.endTemp = algorithmParams.endTemp;
		this.randomNumberGenerator = new Random();
		this.functionParamSize = functionParams.functionParamSize;
	}

	public AbstractFunc getBestSolution ()
	{
		return bestSolution;
	}

	public void initialize(AbstractFunc func)
	{
		solution = func.cloneObject();
		solution.initialize(functionParamSize);
		bestSolution = solution;
	}


	public void run()
	{
		double currentTemperature = startTemp;
		while( currentTemperature > endTemp)
		{
			for (int i = 0; i < iterationsCnt; i++)
			{
				// get a new candidate solution
				// getCandidate function makes sure it is different
				AbstractFunc newSolution = getCandidate(solution);

				// calculate the acceptance probability
				double acceptanceProbability = getAcceptanceProbability(solution, newSolution, currentTemperature);

				// randomly decide whether to accept the new solution
				if( acceptanceProbability > randomNumberGenerator.nextDouble() )
				{
					solution = newSolution;
				}
				// if the best solution is worse than the new one
				// then we update the best solution
				if( solution.evaluate() <= bestSolution.evaluate() )
				{
					bestSolution = solution;
				}
			}
			// cool the temperature
			currentTemperature *= 1 - coolingRate;
		}
	}
	//generate a random(neighbouring) solution
	private AbstractFunc getCandidate(AbstractFunc currentSolution) {
		AbstractFunc candidate = currentSolution.cloneObject();
		candidate.mutate();
		return candidate;
	}

	//if the solution is better, then we accept it
	//If the new solution is worse, calculate an acceptance probability
	private double getAcceptanceProbability(AbstractFunc currentSolution, AbstractFunc newSolution, double temperature){
		if (currentSolution.evaluate() >= newSolution.evaluate()) {
			return 1.0;
		}
		//solution is worse
		return Math.exp((currentSolution.evaluate() - newSolution.evaluate()) / temperature);
	}

}
