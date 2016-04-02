package Neurons;

/**
 * Created by qadirhaqq on 4/1/16.
 */
public class InputNeuron implements Neuron{
    private double value;

    /**
     * Constructor for an InputNeuron
     * @param value - Value of the input neuron
     */
    public InputNeuron(double value){
        this.value = value;
    }
    public void setValue(double value){
        this.value = value;
    }

    /**
     * Compute function
     * @return The value of the input
     */
    @Override
    public double compute() {
        return value;
    }
}
