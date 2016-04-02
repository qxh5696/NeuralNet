package mathematics;

import java.util.ArrayList;

/**
 * Created by qadirhaqq on 4/1/16.
 */

public class Sigmoid implements Function
{
    @Override
    public double calculate(double in) {
        return 1f/((double) Math.exp(-in)+1);
    }
}
