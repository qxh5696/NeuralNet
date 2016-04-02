import Neurons.AccessorNeuron;
import Neurons.InputNeuron;
import Neurons.NeuralNet;
import mathematics.Vector;

import java.util.ArrayList;

/**
 * Created by qadirhaqq on 4/2/16.
 */
public class TestNeuron {

    public static void main(String[] args) {

        double threshold = 1;
        NeuralNet net = new NeuralNet();
        net.addNeurons(3,5,3);
        Vector input = new Vector();
        System.out.println("Result 1 (Neural Net) before iterations: " + net.calculate(input).get(0));

        ArrayList<Vector> inputs = new ArrayList<>();
        ArrayList<Vector> expected = new ArrayList<>();
        input.add(0.0);
        input.add(0.0);
        input.add(0.0);
        inputs.add(input.copy());

        input.clear();
        input.add(1.0);
        input.add(0.0);
        input.add(0.0);
        inputs.add(input.copy());

        input.clear();
        input.add(0.0);
        input.add(1.0);
        input.add(0.0);
        inputs.add(input.copy());

        input.clear();
        input.add(1.0);
        input.add(1.0);
        input.add(0.0);
        inputs.add(input.copy());


        input.clear();
        input.add(0.0);
        input.add(0.0);
        input.add(1.0);
        inputs.add(input.copy());

        input.clear();
        input.add(1.0);
        input.add(0.0);
        input.add(1.0);
        inputs.add(input.copy());


        input.clear();
        input.add(0.0);
        input.add(1.0);
        input.add(1.0);
        inputs.add(input.copy());


        input.clear();
        input.add(1.0);
        input.add(1.0);
        input.add(1.0);
        inputs.add(input.copy());
        input.clear();

        Vector output = new Vector();
        output.add(0.0);
        expected.add(output.copy());
        output.clear();
        output.add(1.0);
        expected.add(output.copy());
        output.clear();
        output.add(0.0);
        expected.add(output.copy());
        output.clear();
        output.add(1.0);
        expected.add(output.copy());
        output.clear();
        output.add(0.0);
        expected.add(output.copy());
        output.clear();
        output.add(1.0);
        expected.add(output.copy());
        output.clear();
        output.add(0.0);
        expected.add(output.copy());
        output.clear();
        output.add(1.0);
        expected.add(output.copy());
        output.clear();
        for(int i = 0; i < 1000; i++) {
            net.update(inputs, expected);
        }
//        //Cheese festival conundrum
//
//        InputNeuron i1 = new InputNeuron(1);
//        InputNeuron i2 = new InputNeuron(0);
//        InputNeuron i3 = new InputNeuron(0);
//
//        AccessorNeuron o1 = new AccessorNeuron(threshold);
//        //AccessorNeuron o2 = new AccessorNeuron(threshold);
//
//        NeuralNet net = new NeuralNet();
//
//        net.addInNeuron(i1);
//        net.addInNeuron(i2);
//        net.addInNeuron(i3);
//        net.addOutNeuron(o1);
//
//        //net.addOutNeuron(o2);
//
//
//        Vector input = new Vector();
//
//        ArrayList<Vector> inputs = new ArrayList<>();
//        ArrayList<Vector> expected = new ArrayList<>();
//        input.add(0.0);
//        input.add(0.0);
//        input.add(0.0);
//        inputs.add(input.copy());
//
//        input.clear();
//        input.add(1.0);
//        input.add(0.0);
//        input.add(0.0);
//        inputs.add(input.copy());
//
//        input.clear();
//        input.add(0.0);
//        input.add(1.0);
//        input.add(0.0);
//        inputs.add(input.copy());
//
//        input.clear();
//        input.add(1.0);
//        input.add(1.0);
//        input.add(0.0);
//        inputs.add(input.copy());
//
//
//        input.clear();
//        input.add(0.0);
//        input.add(0.0);
//        input.add(1.0);
//        inputs.add(input.copy());
//
//        input.clear();
//        input.add(1.0);
//        input.add(0.0);
//        input.add(1.0);
//        inputs.add(input.copy());
//
//
//        input.clear();
//        input.add(0.0);
//        input.add(1.0);
//        input.add(1.0);
//        inputs.add(input.copy());
//
//
//        input.clear();
//        input.add(1.0);
//        input.add(1.0);
//        input.add(1.0);
//        inputs.add(input.copy());
//        input.clear();
//
//        Vector output = new Vector();
//        output.add(0.0);
//        expected.add(output.copy());
//        output.clear();
//        output.add(1.0);
//        expected.add(output.copy());
//        output.clear();
//        output.add(0.0);
//        expected.add(output.copy());
//        output.clear();
//        output.add(1.0);
//        expected.add(output.copy());
//        output.clear();
//        output.add(0.0);
//        expected.add(output.copy());
//        output.clear();
//        output.add(1.0);
//        expected.add(output.copy());
//        output.clear();
//        output.add(0.0);
//        expected.add(output.copy());
//        output.clear();
//        output.add(1.0);
//        expected.add(output.copy());
//        output.clear();
//
//        o1.addNeuron(i1);
//        o1.addNeuron(i2);
//        o1.addNeuron(i3);
//
//        o1.assignWeight(1,0);
//        o1.assignWeight(1,1);
//        o1.assignWeight(1,2);
//
//        input.add(1.0);
//        input.add(1.0);
//        input.add(1.0);


            /*
        NeuralNet net = new NeuralNet();

        InputNeuron i1 = new InputNeuron(1);
        InputNeuron i2 = new InputNeuron(0);

        AccessorNeuron h1 = new AccessorNeuron(threshold);
        AccessorNeuron h2 = new AccessorNeuron(threshold);
        AccessorNeuron h3 = new AccessorNeuron(threshold);

        AccessorNeuron o1 = new AccessorNeuron(threshold);
        h1.addNeuron(i1);
        h1.assignWeight(1,0);

        h2.addNeuron(i1);
        h2.addNeuron(i2);
        h2.assignWeight(1,0);
        h2.assignWeight(1,1);


        h3.addNeuron(i2);
        h3.assignWeight(1,1);

        o1.addNeuron(h1);
        o1.addNeuron(h2);
        o1.assignWeight(1,0);
        o1.assignWeight(1,1);

        ArrayList<Vector> inputs = new ArrayList<>();
        ArrayList<Vector> expected = new ArrayList<>();
        Vector input = new Vector();
        Vector output = new Vector();

        input.add(0.0);
        input.add(0.0);
        inputs.add(input.copy());
        input.clear();

        input.add(1.0);
        input.add(0.0);
        inputs.add(input.copy());
        input.clear();

        input.add(0.0);
        input.add(1.0);
        inputs.add(input.copy());
        input.clear();


        input.add(1.0);
        input.add(1.0);
        inputs.add(input.copy());
        input.clear();


        output.add(0.0);
        expected.add(output.copy());
        output.clear();

        output.add(1.0);
        expected.add(output.copy());
        output.clear();

        output.add(1.0);
        expected.add(output.copy());
        output.clear();

        output.add(1.0);
        expected.add(output.copy());
        output.clear();

        net.addInNeuron(i1);
        net.addInNeuron(i2);

        net.addNeuron(h1);
        net.addNeuron(h2);
        net.addNeuron(h3);

        net.addOutNeuron(o1);


        input.add(1.0);
        input.add(1.0);

        */

        int iterations = 10000;




        System.out.println("Result 2 (Neural Net) after " + iterations + " iterations: " + net.calculate(input).get(0));
    }
}
