package Neurons;

import mathematics.Sigmoid;
import mathematics.Vector;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by qadirhaqq on 4/1/16.
 */
public class AccessorNeuron implements Neuron{
    //the random generator
    private Random rand = new Random();
    //the weights which are used to compute the activation function
    private Vector weights = new Vector();
    //the input neurons
    private ArrayList<Neuron> inputs = new ArrayList<>();
    //the bias (a double between -1 and 0)
    private double bias = -rand.nextDouble();
    //the sigmoid activation function
    private Sigmoid activation = new Sigmoid();

    /**
     * constructs an AccessorNeuron with
     * a random bias
     */
    public AccessorNeuron(){

    }

    /**
     * construts an AccessorNeuron with
     * the specified value
     * @param bias the bias to use
     */
    public AccessorNeuron(double bias){
        this.bias = -bias;
    }

    /**
     * Gets the index of a neuron
     * @param n the neuron to find
     * @return -1 if not found, index if found
     */
    public int getIndex(Neuron n){
        for(int i = 0; i < inputs.size(); i++){
            if(inputs.get(i) == n){
                return i;
            }
        }
        return -1;
    }

    /**
     * gets the number of inputs
     * @return the number of inputs
     */
    public int getSizeInputs(){
        return inputs.size();
    }

    /**
     * Gets the weight of the specified neuron
     * @param i the neuron to find the weight of
     * @return the weight of the specified neuron
     */
    public double getWeight(int i){
        return weights.get(i);
    }

    /**
     * Gets the bias
     * @return the bias
     */
    public double getBias(){
        return bias;
    }

    /**
     * Sets the bias
     * @param i the bias to use
     */
    public void setBias(double i){
        bias = i;
    }

    /**
     * Assigns the weight of a neuron with the specified index
     * @param weight the weight to set the neuron's weight
     * @param index the index of the neuron to set the weight of
     */
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
        for(Neuron v: inputs){
            toReturn.add(v.compute());
        }
        return toReturn;
    }

    /**
     * Computes a Sigmoid activation value
     * @return
     */
    public double compute() {
        return activation.calculate(getInputValues().dotProduct(weights)+ bias) ;
    }

    /**
     * Adds a neuron to the input neurons
     * @param n the neuron to add
     */
    public void addNeuron(Neuron n){
        inputs.add(n);
    }

    /**
     * Gets a specific input neuron
     * @param i the index of the input neuron
     * @return the neuron
     */
    public Neuron getNeuron(int i){
        return inputs.get(i);
    }

    /**
     * Gets the input neurons
     * @return An ArrayList containing the Input Neurons
     */
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
