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

    /**
     * Vector addition
     * @param v the vector the instance is added to
     * @return The summation of the two vectors
     */
    public Vector plus(Vector v){
        Vector acc = new Vector();
        for( int i = 0; i < v.size(); i++){
            acc.add(this.get(i) + v.get(i));
        }
        return acc;
    }

    /**
     * Vector subtraction
     * @param v subtracts the values of the class instance with the vector passed in
     * @return subtracted Vector
     */
    public Vector minus(Vector v){
        Vector acc = new Vector();
        for( int i = 0; i < v.size(); i++){
            acc.add(this.get(i) - v.get(i));
        }
        return acc;
    }

    /**
     * A deep copy of the current vector
     * @return a newly copied vector
     */
    public Vector copy(){
        Vector toReturn = new Vector();
        for(int i = 0; i < size(); i++){
            toReturn.add(new Double(get(i).floatValue()));
        }
        return toReturn;
    }


}
