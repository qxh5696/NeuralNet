package Neurons;

/**
 * Created by qadirhaqq on 4/1/16.
 */
public class InputNeuron implements Neuron{
    private float value;
    /**
     * The input neuron to compute
     * @param value
     */
    public InputNeuron(float value){
        this.value = value;
    }

    @Override
    public float compute() {
        return value;
    }
}
