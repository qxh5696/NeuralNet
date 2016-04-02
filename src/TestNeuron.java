import Neurons.AccessorNeuron;
import Neurons.InputNeuron;

/**
 * Created by qadirhaqq on 4/2/16.
 */
public class TestNeuron {

    public static void main(String[] args) {
        float w1 = 6;
        float w2 = 2;
        float w3 = 2;
        float threshold = 5;


        InputNeuron i1 = new InputNeuron(0);
        InputNeuron i2 = new InputNeuron(1);
        InputNeuron i3 = new InputNeuron(1);

        AccessorNeuron o1 = new AccessorNeuron(threshold);

        o1.addNeuron(i1);
        o1.addNeuron(i2);
        o1.addNeuron(i3);

        o1.assignWeight(w1, 0);
        o1.assignWeight(w2, 1);
        o1.assignWeight(w3, 2);


        float result = o1.compute();
        System.out.println(result);

    }
}
