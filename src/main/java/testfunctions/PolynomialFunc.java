package testfunctions;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PolynomialFunc extends AbstractFunc
{

	final Function function;
	final Expression expression;
	ArrayList<Argument> arguments;

	public PolynomialFunc (String input, Double lowerBound, Double upperBound, String name)
	{
		function = new Function(input);
		List<Double> lowerLimit = new ArrayList<>(function.getParametersNumber());
		List<Double> upperLimit = new ArrayList<>(function.getParametersNumber());
		for (int i = 0; i < function.getParametersNumber(); i++)
		{
			lowerLimit.add(lowerBound);
			upperLimit.add(upperBound);
		}
		setBounds(lowerLimit, upperLimit);
		this.name = name;
		this.variables = new ArrayList<>(function.getParametersNumber());
		this.arguments = new ArrayList<Argument>(function.getParametersNumber());
		for (int i = 0; i < function.getArgumentsNumber(); i++)
		{
			this.arguments.add(function.getArgument(i));
		}
		this.expression = new Expression(function.getFunctionExpressionString(), arguments.toArray(new Argument[0]));
		this.result = expression.calculate();
	}

	public PolynomialFunc (PolynomialFunc polynomialFunc)
	{
		this.variables = new ArrayList<>();
		this.bounds = new ArrayList<>();
		this.arguments = new ArrayList<>();
		this.variables.addAll(polynomialFunc.variables);
		this.bounds.addAll(polynomialFunc.bounds);
		this.arguments.addAll(polynomialFunc.arguments);
		this.function = polynomialFunc.function;
		this.expression = polynomialFunc.expression;
		this.result = polynomialFunc.result;
	}

	@Override public void initialize (int variablesSize)
	{
		Random rand = new Random();
		for (int i = 0; i < variablesSize; i++)
		{
			variables.add(rand.nextDouble(bounds.get(i).getLowerBound(), bounds.get(i).getUpperBound()));
		}
		for (int i = 0; i < variables.size(); i++)
		{
			arguments.get(i).setArgumentValue(variables.get(i));
		}
		this.result = expression.calculate();
	}


	@Override public void mutate ()
	{
		super.mutate();
		for (int i = 0; i < variables.size(); i++)
		{
			arguments.get(i).setArgumentValue(variables.get(i));
		}
		this.result = expression.calculate();
	}

	@Override public Double evaluate ()
	{
		return this.result;
	}

	@Override public AbstractFunc cloneObject ()
	{
		return new PolynomialFunc(this);
	}
}
