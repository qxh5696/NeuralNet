package Neurons;

import mathematics.Vector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Nicholas on 4/2/2016.
 */
public class NeuralNet implements Serializable {
    Random rand = new Random();
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
     * Evaluates the neural net
     * @param input a vector containing the parameters to the neural net
     * @return Output the output of the neural net
     */
    public Vector calculate(Vector input){
        //set all of the input neurons to the parameters' values
        for(int i = 0; i < in.size(); i++){
            if(i<input.size())
            in.get(i).setValue(input.get(i));
        }
        //calculate each of the output values
        Vector output = new Vector();
        for(int i = 0; i < out.size(); i++){
            output.add(out.get(i).compute());
        }
        return output;
    }

    /**
     * The Cost to minimize using the Gradient Descent formula
     * @param input the input to the neural net
     * @param expected Expected output of computation
     * @return a heuristic of how well the model fit
     */
    private double cost(ArrayList<Vector> input, ArrayList<Vector> expected){
        int size = input.size();
        double total = 0;
        for(int i = 0; i <input.size(); i++){
            Vector output = calculate(input.get(i));
            Vector innerDot = output.minus(expected.get(i));
            total += innerDot.dotProduct(innerDot);
        }
        return total/(2*size);
    }

    /**
     * Change in cost function with respect to the bias
     * @param input  a vector containing the parameters to the function
     * @param expected Expected output of computation
     * @param n the neuron who's weight to change
     * @param i Index of the weight
     * @return The derivative of the cost with respect to the Weight
     */
    private double DcostDw(ArrayList<Vector> input, ArrayList<Vector> expected, AccessorNeuron n, int i){
        double cost1 = cost(input, expected);
        //we store the old weight so we can revert
        double oldWeight = n.getWeight(i);
        //the small change in x
        double delta = 0.01;
             n.assignWeight(oldWeight+delta, i);
        double cost2 = cost(input, expected);
        n.assignWeight(oldWeight, i);
        return (cost2-cost1)/delta;
    }

    /**
     * Change in the cost function with respect to the bias
     * @param input  a vector containing the parameters to the function
     * @param expected Expected output of computation
     * @param n Neuron to update
     * @return The derivative of the cost with respect to the Bias
     */
    private double DcostDb(ArrayList<Vector> input, ArrayList<Vector> expected, AccessorNeuron n){
        double cost1 = cost(input, expected);
        double oldBias = n.getBias();
        double delta = 0.01f;
        n.setBias(oldBias + delta);
        double cost2 = cost(input, expected);
        n.setBias(oldBias);
        return (cost2-cost1)/delta;
    }

    /**
     * Update all the weight of an individual node
     * @param input  a vector containing the parameters to the function
     * @param expected Expected output of computation
     * @param n the neurons who's weight to change
     */
    private void updateWeight(ArrayList<Vector> input, ArrayList<Vector> expected, AccessorNeuron n){
        double eta = 10f;
        for(int i = 0; i < n.getSizeInputs(); i++){
            double newWeight = n.getWeight(i)-eta*DcostDw(input, expected, n, i);
            n.assignWeight(newWeight, i);
        }
    }

    /**
     * Updates the weights of all the neurons
     * @param input a vector containing the parameters to the function
     * @param expected Expected output of computation
     */
    private void updateWeight(ArrayList<Vector> input, ArrayList<Vector> expected){
        for(Neuron n: neurons){
            if(n instanceof AccessorNeuron){
                updateWeight(input, expected, (AccessorNeuron) n);
            }
        }
    }

    /**
     * Update the bias of an individual neuron
     * @param input  a vector containing the parameters to the function
     * @param expected Expected output of computation
     * @param n The neuron who's basis to change
     */
    private void updateBias(ArrayList<Vector> input, ArrayList<Vector> expected, AccessorNeuron n){
        double eta = 10f;
        double newBias = n.getBias()-eta*DcostDb(input, expected, n);
        n.setBias(newBias);
    }

    /**
     * Loops through the list of neurons and changes all
     * the neurons' basis using the updateBias function
     * @param input  a vector containing the parameters to the function
     * @param expected Expected output of computation
     */
    private void updateBias(ArrayList<Vector> input, ArrayList<Vector> expected){
        for(Neuron n: neurons){
            if(n instanceof AccessorNeuron){
                updateBias(input, expected, (AccessorNeuron) n);
            }
        }
    }

    /**
     * Trains the neural net with the given data
     * @param input  a vector containing the parameters to the function
     * @param expected Expected output of computation
     */
    public void update(ArrayList<Vector> input, ArrayList<Vector> expected){
        updateWeight(input,expected);
        updateBias(input,expected);
    }
    public LinkedList<AccessorNeuron> addHiddenNeuron(int n){
        LinkedList<AccessorNeuron> layer = new LinkedList<>();
        for(int i = 0; i < n; i++){
            AccessorNeuron toAdd = new AccessorNeuron();
            layer.add(toAdd);
            neurons.add(toAdd);
        }
        return layer;
    }
    public LinkedList<AccessorNeuron> addOutputNeuron(int n){
        LinkedList<AccessorNeuron> layer = new LinkedList<>();
        for(int i = 0; i < n; i++){
            AccessorNeuron toAdd = new AccessorNeuron();
            layer.add(toAdd);
            addOutNeuron(toAdd);
        }
        return layer;
    }
    public LinkedList<InputNeuron> addInputNeuron(int n){
        LinkedList<InputNeuron> layer = new LinkedList<>();
        for(int i = 0; i < n; i++){
            InputNeuron toAdd = new InputNeuron(n);
            layer.add(toAdd);
            addInNeuron(toAdd);
        }
        return layer;
    }
    public void linkUp(LinkedList<Neuron> layer1, LinkedList<AccessorNeuron> layer2){
        for(int i1 = 0; i1 < layer1.size(); i1++){
            for(int i2 = 0; i2 < layer2.size(); i2++){
                layer2.get(i2).addNeuron(layer1.get(i1),20*(rand.nextFloat()-0.5));
            }
        }
    }

    public void addNeurons(int inputNum, int hiddenNum, int outputNum){
        LinkedList<InputNeuron> inLayer = addInputNeuron(inputNum);
        LinkedList<AccessorNeuron> hiddenLayer = addHiddenNeuron(hiddenNum);
        LinkedList<AccessorNeuron> outLayer = addOutputNeuron(outputNum);
        LinkedList<Neuron> inLayerNeuron = new LinkedList<>();
        for(InputNeuron in : inLayer){
            inLayerNeuron.add(in);
        }
        LinkedList<Neuron> AccessorLayerNeuron = new LinkedList<>();
        for(AccessorNeuron in : hiddenLayer){
            AccessorLayerNeuron.add(in);
        }
        linkUp(inLayerNeuron,hiddenLayer);
        linkUp(AccessorLayerNeuron,outLayer);
    }

}
