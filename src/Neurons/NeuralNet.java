package Neurons;

import java.util.ArrayList;

/**
 * Created by Nicholas on 4/2/2016.
 */
public class NeuralNet {
    private ArrayList<InputNeuron> in = new ArrayList<>();
    private ArrayList<AccessorNeuron> out = new ArrayList<>();
    private ArrayList<Neuron> neurons = new ArrayList<>();

    public void addNeuron(Neuron n){
        neurons.add(n);
    }

    public void addInNeuron(InputNeuron n){
        in.add(n);
        neurons.add(n);
    }
    public void addOutNeuron(AccessorNeuron n){
        out.add(n);
        neurons.add(n);
    }
    public ArrayList<Float> calculate(ArrayList<Float> input){
        for(int i = 0; i < in.size(); i++){
            in.get(i).setValue(input.get(i));
        }
        ArrayList<Float> output = new ArrayList<>();
        for(int i = 0; i < out.size(); i++){
            output.add(out.get(i).compute());
        }
        return output;
    }
}
