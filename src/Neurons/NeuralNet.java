package Neurons;

import mathematics.Vector;

import java.util.ArrayList;

/**
 * Created by Nicholas on 4/2/2016.
 */
public class NeuralNet {
    private ArrayList<InputNeuron> in = new ArrayList<>();//List of input neurons
    private ArrayList<AccessorNeuron> out = new ArrayList<>();//List of output neurons
    private ArrayList<Neuron> neurons = new ArrayList<>();//List of all Neurons

    /**
     * Adds a neuron to the list of all neurons
     * @param n Neuron
     */
    public void addNeuron(Neuron n){
        neurons.add(n);
    }

    /**
     * Adds an input neuron to the in list
      * @param n InNeuron to add
     */
    public void addInNeuron(InputNeuron n){
        in.add(n);
        neurons.add(n);
    }

    /**
     * Adds an output neuron to the out list
     * @param n OutNeuron to add
     */
    public void addOutNeuron(AccessorNeuron n){
        out.add(n);
        neurons.add(n);
    }

    /**
     * Computation of determinant
     * @param input Input Vector to take as a parameter
     * @return Output vector to return
     */
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

    /**
     * The Cost to minimize using the Gradient Descent formula
     * @param values Sample input passed in through test file
     * @param expected Expected output passed in through test file
     * @return The minimum we can change our values by and still remain within range of our expected output
     */
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

    /**
     * Change in cost function with respect to the bias
     * @param values Sample input passed in through test file
     * @param expected Expected output passed in through test file
     * @param n Neuron obtained through the for-loo
     * @param i Index of the weight
     * @return The derivative of the cost with respect to the Weight
     */
    private double DcostDw(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n, int i){
        double cost1 = cost(values, expected);
        double oldWeight = n.getWeight(i);
        double delta = 0.01f;
        n.assignWeight(oldWeight+delta, i);
        double cost2 = cost(values, expected);
        n.assignWeight(oldWeight, i);
        return (cost2-cost1)/delta;
    }

    /**
     * Change in the cost function with respect to the bias
     * @param values Sample input passed in through test file
     * @param expected Expected output passed in through test file
     * @param n Neuron to update
     * @return The derivative of the cost with respect to the Bias
     */
    private double DcostDb(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n){
        double cost1 = cost(values, expected);
        double oldBias = n.getBias();
        double delta = 0.01f;
        n.setBias(oldBias + delta);
        double cost2 = cost(values, expected);
        n.setBias(oldBias);
        return (cost2-cost1)/delta;
    }

    /**
     * Update the weight of an individual node
     * @param values Sample input passed in through test file
     * @param expected Expected output passed in through test file
     * @param n Neuron obtained through the for-loop
     */
    private void updateWeight(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n){
        double eta = 10f;
        for(int i = 0; i < n.getSizeInputs(); i++){
            double newWeight = n.getWeight(i)-eta*DcostDw(values, expected, n, i);
            n.assignWeight(newWeight, i);
        }
    }

    /**
     * Updates the weights of the neurons in the neuron list
     * @param values Sample input passed in through test file
     * @param expected Expected output passed in through test file
     */
    private void updateWeight(ArrayList<Vector> values, ArrayList<Vector> expected){
        for(Neuron n: neurons){
            if(n instanceof AccessorNeuron){
                updateWeight(values, expected, (AccessorNeuron) n);
            }
        }
    }

    /**
     * Update the bias of an individual neuron
     * @param values Sample input
     * @param expected Expected output
     * @param n Neuron n
     */
    private void updateBias(ArrayList<Vector> values, ArrayList<Vector> expected, AccessorNeuron n){
        double eta = 10f;
        double newBias = n.getBias()-eta*DcostDb(values, expected, n);
        n.setBias(newBias);
    }

    /**
     * Loops through the list of neurons and changes neurons based on updateBias function
     * @param values The list of values input
     * @param expected Expected output to try and map
     */
    private void updateBias(ArrayList<Vector> values, ArrayList<Vector> expected){
        for(Neuron n: neurons){
            if(n instanceof AccessorNeuron){
                updateBias(values, expected, (AccessorNeuron) n);
            }
        }
    }

    /**
     * "Trains" the neural net
     * @param input Sample input given a problem
     * @param expected Expected output given
     */
    public void update(ArrayList<Vector> input, ArrayList<Vector> expected){
        updateWeight(input,expected);
        updateBias(input,expected);
    }
}
