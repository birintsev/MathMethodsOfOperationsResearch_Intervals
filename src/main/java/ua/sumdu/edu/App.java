package ua.sumdu.edu;

import ua.sumdu.edu.entities.functions.D2.MyTransformedFunction2D;
import ua.sumdu.edu.entities.functions.D2.meta.Function2D;
import ua.sumdu.edu.entities.functions.D2.meta.TransformedFunction2D;
import ua.sumdu.edu.entities.intervals.D2.decart.DividedInterval;
import ua.sumdu.edu.entities.points.D2.meta.Point2D;

public class App  {

    private static final double tau = (1 + Math.sqrt(5)) / 2;

    public static void main( String[] args ) {
        TransformedFunction2D transformedFunction2D = MyTransformedFunction2D.getInstance();
        double eps = 1E-15;
        DividedInterval dividedInterval = new DividedInterval(0,1);
        Point2D minimum;
        while (dividedInterval.getLength() >= eps) {
            dividedInterval.divide(dividedInterval.getLength() / tau);
            dividedInterval = selectFunMinimum(dividedInterval, transformedFunction2D.getSubstituted());
            System.out.println("The new interval is (" + dividedInterval.getA() + ";" + dividedInterval.getB() + ")");
        }
        minimum = new Point2D(transformedFunction2D.substToArg((dividedInterval.getA() + dividedInterval.getB()) / 2)
                , transformedFunction2D.count(
                transformedFunction2D.substToArg((dividedInterval.getA() + dividedInterval.getB()) / 2)
        ));
        System.out.println("The function reaches its minimum at the " + minimum);
    }

    private static DividedInterval selectFunMinimum(DividedInterval previousInterval, Function2D function2D) {
        return Math.min(
                    function2D.count(previousInterval.getLeft().getA())
                    , function2D.count(previousInterval.getLeft().getB()))
                < Math.min(
                    function2D.count(previousInterval.getRight().getA())
                    , function2D.count(previousInterval.getRight().getB()))
                ?
                previousInterval.getLeft() : previousInterval.getRight();
    }
}