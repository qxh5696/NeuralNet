package mathematics;

import java.util.ArrayList;

/**
 * Created by qadirhaqq on 4/1/16.
 */

public class Sigmoid implements Function
{
    /**
     * The sigmoid activation function
     * @param in the input to the function (e.g, sigmoid function computed using "in")
     * @return The activation value for which we assess the input to outputs
     */
    @Override
    public double calculate(double in) {
        return 1f/(Math.exp(-in)+1);
    }
}
