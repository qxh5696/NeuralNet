package ThoughtSensor;

import Neurons.NeuralNet;
import data_processing.GaussianDistribution;
import mathematics.Vector;
import oscP5.OscEventListener;
import oscP5.OscMessage;
import oscP5.OscP5;
import oscP5.OscStatus;
import views.ReadNet2;
import data_processing.MuseOSCServer;

import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by qadirhaqq on 4/3/16.
 */
public class ThoughtSensor {
    private int iterations;
    ArrayList<Vector> inputList = new ArrayList<>();
    ArrayList<Vector> expected = new ArrayList<>();
    NeuralNet net;

    public ThoughtSensor(String fileName, int iterations){
        net = ReadNet2.getNet(fileName);
        int recvPort = 5000;
        this.iterations = iterations;

        MuseOSCServer museOSCServer = new MuseOSCServer();

        GaussianDistribution g = new GaussianDistribution(1750, 0, 40);
        museOSCServer.museServer = new OscP5(museOSCServer,recvPort);
        museOSCServer.museServer.addListener(new OscEventListener() {

            @Override
            public void oscEvent(OscMessage oscMessage) {
                if(oscMessage.checkAddrPattern("/muse/eeg")){

                    //THERES GOT TO BE A BETTER WAY TO DO THIS IT'S 4 IN THE MORNING SOMEONE PLEASE HELP ME
                    double [] testIndicies = new double[4];
                    for(int i = 0; i < 4; ++i){
                        float d = oscMessage.get(i).floatValue();
                        testIndicies[i] = (double)d;
                    }
                    boolean[] values = g.testIndices(testIndicies);
                    for(int i = 0; i < values.length; ++i){
                        if(values[i])
                            testIndicies[i] = 1.0;
                        else
                            testIndicies[i] = 0.0;
                    }
                    Vector input = new Vector();
                    for(double d : testIndicies){
                        input.add(d);
                    }
                    inputList.add(input); //Amass all the input
                    Vector v = net.calculate(input);
                    if(v.get(0)==1){
                        System.out.println("left");
                    }
                    if(v.get(1)==1){
                        System.out.println("right");
                    }
                }

            }

            @Override
            public void oscStatus(OscStatus oscStatus) {

            }
        });
    }
    public void train(){

        Vector left = new Vector(1., 0., 0., 0., 0., 0.);
        Vector right = new Vector(0., 1., 0., 0., 0., 0.);
        Vector down = new Vector(0., 0., 1., 0., 0., 0.);
        Vector up = new Vector(0., 0., 0., 1., 0., 0.);
        Vector leftClick = new Vector(0., 0., 0., 0., 1., 0.);
        Vector rightClick = new Vector(0., 0., 0., 0., 0., 1.);

        expected.add(left);
        expected.add(right);
        expected.add(down);
        expected.add(up);
        expected.add(leftClick);
        expected.add(rightClick);

        for(int i = 0; i < this.iterations; ++i){
            net.update(inputList, expected);
        }
    }

    public static void main(String[] args) {
        ThoughtSensor ts = new ThoughtSensor("database",5);
    }

}
