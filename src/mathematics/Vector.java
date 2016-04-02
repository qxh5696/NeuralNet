package mathematics;

import java.util.ArrayList;

/**
 * Created by qadirhaqq on 4/1/16.
 */

/**
 * Vector implementation
 */
public class Vector extends ArrayList<Float> {

    /**
     * Computes the dot product between two vectors
     * @param v1 vector 1
     * @param v2 vector 2
     * @return a float value of the dot product
     */
    public float dotProduct(Vector v1, Vector v2){
        float dotProduct = 0;
        for(int i = 0; i < v1.size(); ++i){
            dotProduct += v1.get(i) * v2.get(i);
        }
        return dotProduct;
    }




}
