package mathematics;

import java.util.ArrayList;

/**
 * Created by qadirhaqq on 4/1/16.
 */

/**
 * Vector implementation
 */
public class Vector extends ArrayList<Double> {
    /**
     * Computes the dot product between two vectors
     * @param v vector one
     * @return a double value of the dot product
     */
    public double dotProduct(Vector v){
        double dotProduct = 0;
        for(int i = 0; i < v.size(); ++i){
            dotProduct += v.get(i) * this.get(i);
        }
        return dotProduct;
    }
    public Vector plus(Vector v){
        Vector acc = new Vector();
        for( int i = 0; i < v.size(); i++){
            acc.add(v.get(i) + v.get(i));
        }
        return acc;
    }
    public Vector minus(Vector v){
        Vector acc = new Vector();
        for( int i = 0; i < v.size(); i++){
            acc.add(v.get(i) - v.get(i));
        }
        return acc;
    }



}
