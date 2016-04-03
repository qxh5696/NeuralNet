import Neurons.NeuralNet;
import mathematics.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import oscP5.*;

import data_processing.MuseOSCServer;
import data_processing.GaussianDistribution;
import data_processing.Parser;
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
        //create the server/


        MuseOSCServer museOSCServer;
        int recvPort = 5000;
        museOSCServer = new MuseOSCServer();
        museOSCServer.museServer = new OscP5(museOSCServer,recvPort);


        int iterations = 1000;


        //create the probability distributions
        GaussianDistribution g = new GaussianDistribution( 1750, 0, 10);
        //the bins
        g.calculateIndices();
        double[] fourBuffer = new double[4];
        //ArrayList<Double> indicies = new ArrayList<>();
        //read data
        try {

            //InetAddress serverAddr = InetAddress.getByName("127.0.0.1");
//            Socket clientSocket = new Socket("127.0.0.1", 5000);
            ServerSocket serverSocket = new ServerSocket(5000);
            Socket clientSocket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String s;

            while ( (s=bufferedReader.readLine()) != null) {
                double[] list = Parser.parseLine(s);
                if(list != null){
                    for(int i = 0; i < list.length; ++i){
                        fourBuffer[i] = list[i];
                    }
                    boolean[] values = g.testIndices(fourBuffer);
                    for(int i = 0; i < values.length; ++i){
                        if(values[i]){
                            input.add(1.0);
                        }else{
                            input.add(0.0);
                        }
                    }
                    inputs.add(input.copy());
                    File f = new File("outputTest");//What i think this will do is overwrite it but I'm not sure
                    expected.add(new Vector());
                    expected.get(0).add(1.);
                    expected.get(0).add(0.);
                    expected.get(0).add(0.);
                    expected.get(0).add(0.);
                    expected.get(0).add(0.);
                    expected.get(0).add(0.);
                    for(int i = 0; i < iterations; i++) {
                        net.update(inputs, expected);
                    }
//                    try {
//                        FileOutputStream fout = new FileOutputStream(f);
//                        ObjectOutputStream oos = new ObjectOutputStream(fout);
//                        oos.writeObject(net);
//                        oos.close();
//                        fout.close();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    finally {
                        System.out.println("CHEESE");
                        System.out.println("Result 2 (Neural Net) after " + iterations + " iterations: " + net.calculate(input).get(0));
                        inputs.clear();
                        input.clear();
                        expected.clear();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //same as indicies
        /*
        boolean[] values = g.testIndices();
        Vector out = new Vector();
        for(int i = 0; i < values.length; ++i){
            if(values[i]){
                //TODO mod by 10
                input.add(1.0);
                continue;

            }else {
                input.add(0.0);
            }
        }
        inputs.add(input.copy());
        input.clear();*/
        //System.out.println("Result 1 (Neural Net) before " + iterations + " iterations: "
        //        + net.calculate(input).get(0));
//        File f = new File("outputTest");//What i think this will do is overwrite it but I'm not sure
//        expected.add(new Vector());
//        expected.get(0).add(1.);
//        expected.get(0).add(0.);
//        expected.get(0).add(0.);
//        expected.get(0).add(0.);
//        expected.get(0).add(0.);
//        expected.get(0).add(0.);
//
//        for(int i = 0; i < iterations; i++) {
//            net.update(inputs, expected);
//        }
//        try {
//            FileOutputStream fout = new FileOutputStream(f);
//            ObjectOutputStream oos = new ObjectOutputStream(fout);
//            oos.writeObject(net);
//            oos.close();
//            fout.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("CHEESE");
//
//        System.out.println("Result 2 (Neural Net) after " + iterations + " iterations: " + net.calculate(input).get(0));
    }
}
