package Neurons;

import mathematics.Vector;

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
    public Vector calculate(Vector input){
        for(int i = 0; i < in.size(); i++){
            in.get(i).setValue(input.get(i));
        }
        Vector output = new Vector();
        for(int i = 0; i < out.size(); i++){
            output.add(out.get(i).compute());
        }
        return output;
    }
    private float cost(ArrayList<Vector> values, ArrayList<Vector> expected){
        int size = values.size();
        float total = 0;
        for(int i = 0; i <values.size(); i++){
            Vector output = calculate(values.get(i));
            Vector innerDot = output.minus(expected.get(i));
            total += innerDot.dotProduct(innerDot);
        }
        return total/(2*size);
    }
    private float DcostDw(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n, int i){
        float cost1 = cost(values, expected);
        float oldWeight = n.getWeight(i);
        float delta = 0.01f;
        n.assignWeight(oldWeight+delta, i);
        float cost2 = cost(values, expected);
        n.assignWeight(oldWeight, i);
        return (cost2-cost1)/delta;
    }
    private float DcostDb(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n){
        float cost1 = cost(values, expected);
        float oldBias = n.getBias();
        float delta = 0.01f;
        n.setBias(oldBias + delta);
        float cost2 = cost(values, expected);
        n.setBias(oldBias);
        return (cost2-cost1)/delta;
    }
    private void updateWeight(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n){
        float eta = 0.1f;
        for(int i = 0; i < n.getSizeInputs(); i++){
            float newWeight = n.getWeight(i)-eta*DcostDw(values, expected, n, i);
            n.assignWeight(newWeight, i);
        }
    }
    private void updateWeight(ArrayList<Vector> values, ArrayList<Vector> expected){
        for(Neuron n: neurons){
            if(n instanceof AccessorNeuron){
                updateWeight(values, expected, (AccessorNeuron) n);
            }
        }
    }
    private void updateBias(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n){
        float eta = 0.1f;
        float newBias = n.getBias()-eta*DcostDb(values, expected, n);
        n.setBias(newBias);
    }
    private void updateBias(ArrayList<Vector> values, ArrayList<Vector> expected){
        for(Neuron n: neurons){
            if(n instanceof AccessorNeuron){
                updateBias(values, expected, (AccessorNeuron) n);
            }
        }
    }
    private void update(ArrayList<Vector> values, ArrayList<Vector> expected){
        updateWeight(values,expected);
        updateBias(values,expected);
    }
}
