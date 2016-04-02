package mathematics;

import java.util.ArrayList;

/**
 * Created by qadirhaqq on 4/1/16.
 */
public class Sigmoid implements Function
{
    @Override
    public float calculate(Float in) {
        return 1f/((float) Math.exp(-in+1)+1);
    }
}
