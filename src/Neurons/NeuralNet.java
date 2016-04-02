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
            //out.get(0).addNeuron(in.get(i));
            //TODO must add some way to
        }
        Vector output = new Vector();
        for(int i = 0; i < out.size(); i++){
            output.add(out.get(i).compute());
        }
        return output;
    }
    private double cost(ArrayList<Vector> values, ArrayList<Vector> expected){
        int size = values.size();
        double total = 0;
        for(int i = 0; i <values.size(); i++){
            Vector output = calculate(values.get(i));
            Vector innerDot = output.minus(expected.get(i));
            total += innerDot.dotProduct(innerDot);
        }
        return total/(2*size);
    }
    private double DcostDw(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n, int i){
        double cost1 = cost(values, expected);
        double oldWeight = n.getWeight(i);
        double delta = 0.01f;
        n.assignWeight(oldWeight+delta, i);
        double cost2 = cost(values, expected);
        n.assignWeight(oldWeight, i);
        return (cost2-cost1)/delta;
    }
    private double DcostDb(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n){
        double cost1 = cost(values, expected);
        double oldBias = n.getBias();
        double delta = 0.01f;
        n.setBias(oldBias + delta);
        double cost2 = cost(values, expected);
        n.setBias(oldBias);
        return (cost2-cost1)/delta;
    }
    private void updateWeight(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n){
        double eta = 10f;
        for(int i = 0; i < n.getSizeInputs(); i++){
            double newWeight = n.getWeight(i)-eta*DcostDw(values, expected, n, i);
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
        double eta = 10f;
        double newBias = n.getBias()-eta*DcostDb(values, expected, n);
        n.setBias(newBias);
    }
    private void updateBias(ArrayList<Vector> values, ArrayList<Vector> expected){
        for(Neuron n: neurons){
            if(n instanceof AccessorNeuron){
                updateBias(values, expected, (AccessorNeuron) n);
            }
        }
    }
    public void update(ArrayList<Vector> input, ArrayList<Vector> expected){
        updateWeight(input,expected);
        updateBias(input,expected);
    }
}
