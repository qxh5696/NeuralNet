package Neurons;

import mathematics.Sigmoid;
import mathematics.Vector;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by qadirhaqq on 4/1/16.
 */
public class AccessorNeuron implements Neuron{
    private Random rand = new Random();
    private Vector weights = new Vector();
    private ArrayList<Neuron> inputs = new ArrayList<>();
    private float bias = -rand.nextFloat() * 50; //TODO if broken, change value
    private Sigmoid activation = new Sigmoid();

    public AccessorNeuron(){

    }
    public AccessorNeuron(float bias){
        this.bias = bias;
    }
    public void assignWeight(float weight, int index){
        if(index >= weights.size()){
            weights.add(weight);
        }else {
            weights.set(index, weight);
        }
    }

    /**
     * Obtains the inputs of all the vectors which input
     * into this Neuron
     * @return
     */
    private Vector getInputValues(){
        Vector toReturn = new Vector();
        for(Neuron v: inputs){
            toReturn.add(v.compute());
        }
        return toReturn;
    }

    /**
     * Computes a Sigmoid activation value
     * @return
     */
    public float compute() {
        return activation.calculate(getInputValues().dotProduct(weights)+ bias) ;
    }

    public void addNeuron(Neuron n){
        inputs.add(n);
    }

    public Neuron getNeuron(int i){
        return inputs.get(i);
    }

    public ArrayList<Neuron> getNeuron(){
        return inputs;
    }


}
