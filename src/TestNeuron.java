import Neurons.NeuralNet;
import mathematics.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

import oscP5.*;

import data_processing.*;
import views.*;

/**
 * Created by qadirhaqq on 4/2/16.
 */
public class TestNeuron {
    TestNeuron(){
        SaveNet sn = new SaveNet();
    }
    public static void main(String[] args) {

        //TestNeuron tn = new TestNeuron();

        //double threshold = 1;
        NeuralNet net = new NeuralNet();
        net.addNeurons(40,23,6);

        Vector input = new Vector();
        ArrayList<Vector> inputs = new ArrayList<>();
        ArrayList<Vector> expected = new ArrayList<>();

        /////////////MUSE OSC SERVER////////////////////
        //create the server
        MuseOSCServer museOSCServer;
        int recvPort = 5000;
        museOSCServer = new MuseOSCServer();
        museOSCServer.museServer = new OscP5(museOSCServer,recvPort);

        int iterations = 10000;
        //create the probability distributions
        GaussianDistribution g = new GaussianDistribution( 875, 0, 10);
        //the bins
        g.calculateIndices();
        ArrayList<Double> indicies = new ArrayList<>();
        //read data
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("recording.csv")));
            String s;

            while ( (s=bufferedReader.readLine()) != null) {
                double[] list = Parser.parseLine(s);
                if(list != null){
                    for(double d : list){
                        indicies.add(d);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //same as indicies
        double[] newIndicies = new double[indicies.size()];
        for(int i = 0; i < indicies.size(); ++i){
            newIndicies[i] = indicies.get(i);
        }
        indicies.clear();//give some memory back, though it's probably pointless
        boolean[] values = g.testIndices(newIndicies);
        Vector out = new Vector();

        for(int i = 0; i < values.length; ++i){
            if(values[i]){
                input.add(1.0);
                inputs.add(input.copy());
                input.clear();
                continue;
            }
            input.add(0.0);
            inputs.add(input.copy());
            input.clear();
        }
        //TODO Must quantify data into input vectors!
        //System.out.println("Result 1 (Neural Net) before " + iterations + " iterations: "
        //        + net.calculate(input).get(0));
        File f = new File("outputTest");//What i think this will do is overwrite it but I'm not sure
        for(int i = 0; i < iterations; i++) {
            net.update(inputs, expected);
        }
        try {
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(net);
            oos.close();
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("CHEESE");

        System.out.println("Result 2 (Neural Net) after " + iterations + " iterations: " + net.calculate(input).get(0));
    }
}
