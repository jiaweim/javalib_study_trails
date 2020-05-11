package trails.mockito;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Nov 2019, 2:12 PM
 */
public class MathApplication
{
    private CalculatorService calculatorService;

    public void setCalculatorService(CalculatorService calculatorService)
    {
        this.calculatorService = calculatorService;
    }

    public double add(double input1, double input2)
    {
        return calculatorService.add(input1, input2);
    }

    public double subtract(double input1, double input2)
    {
        return calculatorService.subtract(input1, input2);
    }

    public double multiply(double input1, double input2)
    {
        return calculatorService.multiply(input1, input2);
    }

    public double divide(double input1, double input2)
    {
        return calculatorService.divide(input1, input2);
    }
}
