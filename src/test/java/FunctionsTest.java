import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testfunctions.RosenbrockFunc;

public class FunctionsTest
{
	@Test
	void RosenbrockBuildsProperly()
	{

		int variables = 2;
		Double upperBound = 5.12;
		Double lowerBound = -5.12;
		RosenbrockFunc func = new RosenbrockFunc(2, lowerBound, upperBound, "Rosenbrock");


		Assertions.assertEquals(func.getNumberOfVariables(), variables);

		Assertions.assertEquals(func.bounds.get(0).getLowerBound(),-5.12);
		Assertions.assertEquals(func.bounds.get(0).getUpperBound(),5.12);
		Assertions.assertEquals(func.bounds.get(func.getNumberOfVariables()-1).getLowerBound(),-5.12) ;
		Assertions.assertEquals(func.bounds.get(func.getNumberOfVariables()-1).getUpperBound(),5.12) ;
	}
}
