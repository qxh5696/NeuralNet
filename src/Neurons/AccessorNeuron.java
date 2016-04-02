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
        this.bias = -bias;
    }
    public int getIndex(Neuron n){
        for(int i = 0; i < inputs.size(); i++){
            if(inputs.get(i) == n){
                return i;
            }
        }
        return -1;
    }
    public int getSizeInputs(){
        return inputs.size();
    }
    public float getWeight(int i){
        return weights.get(i);
    }
    public float getBias(){
        return bias;
    }
    public void setBias(float i){
        bias = i;
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

    /**
     * Assign a weight to an input value
     * @param n The neuron
     * @param weight The weight of the neuron
     */
    public void addNeuron(Neuron n, float weight){
        inputs.add(n);
        weights.add(weight);
    }

}
