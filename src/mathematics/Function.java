package mathematics;

import java.util.ArrayList;

/**
 * Created by qadirhaqq on 4/1/16.
 */

/**
 * A basic strategy interface for Functions
 */
public interface Function {
    /**
     * Calculate function for implementation of NeuralNet
     * @param in the input to the function (e.g, sigmoid function computed using "in")
     * @return Result of function
     */
    double calculate(double in);
}
