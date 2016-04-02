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
    private double bias = -rand.nextDouble() * 50; //TODO if broken, change value
    private Sigmoid activation = new Sigmoid();

    public AccessorNeuron(){

    }
    public AccessorNeuron(double bias){
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
    public double getWeight(int i){
        return weights.get(i);
    }
    public double getBias(){
        return bias;
    }
    public void setBias(double i){
        bias = i;
    }
    public void assignWeight(double weight, int index){
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
        System.out.println("Inputs.size(): " + inputs.size());
        for(Neuron v: inputs){
            toReturn.add(v.compute());
        }
        System.out.println("toReturn.size: " + toReturn.size());
        return toReturn;
    }

    /**
     * Computes a Sigmoid activation value
     * @return
     */
    public double compute() {
        Vector vector = getInputValues();
        return activation.calculate(vector.dotProduct(weights)+ bias) ;
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
    public void addNeuron(Neuron n, double weight){
        inputs.add(n);
        weights.add(weight);
    }

}
