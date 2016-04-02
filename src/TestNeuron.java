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
        double threshold = 0;


        InputNeuron i1 = new InputNeuron(1);//A
        InputNeuron i2 = new InputNeuron(1);//B

        AccessorNeuron h1 = new AccessorNeuron(threshold);//C
        AccessorNeuron h2 = new AccessorNeuron(threshold);//D
        AccessorNeuron h3 = new AccessorNeuron(threshold);//E


        AccessorNeuron o1 = new AccessorNeuron(threshold);//F
        AccessorNeuron o2 = new AccessorNeuron(threshold);//G


        //C
        h1.addNeuron(i1);
        h1.assignWeight(1,0);//assign a weight value of 1 to index 0


        //D
        h2.addNeuron(i1);
        h2.addNeuron(i2);
        h2.assignWeight(-2,0);//assign a weight value of -2 to index 0
        h2.assignWeight(2,1);//assign a weight value of 2 to index 1


        //E
        h3.addNeuron(i2);
        h3.assignWeight(-1,0);//Assign a weight value of -1 to index 0

        //F
        o1.addNeuron(h1);
        o1.addNeuron(h2);

        o1.assignWeight(1, 0);//Assign a weight value of 1 to index 0
        o1.assignWeight(-1, 1);//Assign a weight value of -1 to index 1

        //G
        o2.addNeuron(h2);
        o2.addNeuron(h3);

        o2.assignWeight(1, 0);//Assign a weight value of 1 to index 0
        o2.assignWeight(-1, 1);//Assign a weight value of -1 to index 1


        double result1 = o1.compute();
        double result2 = o2.compute();

        NeuralNet net = new NeuralNet();
        net.addInNeuron(i1);
        net.addInNeuron(i2);
        net.addNeuron(h1);
        net.addNeuron(h2);
        net.addNeuron(h3);
        net.addOutNeuron(o1);
        net.addOutNeuron(o2);
        Vector input = new Vector();
        input.add(1.0);
        input.add(1.0);
        System.out.println("Result 1 (Neural Net): "+net.calculate(input).get(0) + "," + "Result 2 (Neural Net): " + net.calculate(input).get(1));
        System.out.println("Result 1: " + result1 + " Result 2: " + result2 );
    }
}
