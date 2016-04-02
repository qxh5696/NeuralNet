package Neurons;

/**
 * Created by qadirhaqq on 4/1/16.
 */
public class InputNeuron implements Neuron{
    private double value;
    /**
     * The input neuron to compute
     * @param value
     */
    public InputNeuron(double value){
        this.value = value;
    }
    public void setValue(double value){
        this.value = value;
    }
    @Override
    public double compute() {
        return value;
    }
}
