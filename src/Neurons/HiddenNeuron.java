package Neurons;

import mathematics.Sigmoid;
import mathematics.Vector;

import java.util.ArrayList;

/**
 * Created by qadirhaqq on 4/1/16.
 */
public class HiddenNeuron implements Neuron{
    private Vector weights = new Vector();
    private ArrayList<Neuron> inputs = new ArrayList<>();
    private float bias = (float) -Math.random();



    public void assignWeight(float weight, int index){
        weights.set(index, weight);
    }

    /**
     * Obtains the inputs of all the vectors which input
     * into this Neuron
     * @return
     */
    private Vector getInput(){
        Vector toReturn = new Vector();
        for(Neuron v: inputs){
            toReturn.add(v.compute());
        }
        return toReturn;
    }


    /**
     * C
     * @return
     */
    public float compute() {
        Sigmoid s = new Sigmoid();
        return s.calculate(getInput().dotProduct(weights));
    }
}
