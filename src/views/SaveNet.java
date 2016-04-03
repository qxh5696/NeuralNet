package views;

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

import Neurons.NeuralNet;
import data_processing.GaussianDistribution;
import data_processing.MuseOSCServer;
import data_processing.Parser;
import mathematics.Vector;
import oscP5.OscEventListener;
import oscP5.OscMessage;
import oscP5.OscP5;
import oscP5.OscStatus;

/**
 * Created by Nicholas on 4/2/2016.
 */
 public class SaveNet extends JFrame {
    public SaveNet() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jp.add(fileName);
        fileName.setColumns(5);
        addCommand = new JButton("Start recording");
        addCommand.addActionListener(al);
        addCommand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCommand.setText("Stop Recording");
                setVisible(false);
                dispose();
                //addCommand.addMouseListener(new );

            }
        });
        jp.add(addCommand);
        add(jp);
        this.setSize(100, 100);
        this.setVisible(true);
    }

    JButton addCommand;
    JPanel jp = new JPanel(new FlowLayout());
    JTextField fileName = new JTextField();
    ActionListener al = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            processKeys();
        }
        private void processKeys() {
            String text = fileName.getText();
            if(text.equals("left")){
                Parser.parse();
                addCommand.setText("Recording left");
                System.out.println("left");
                ArrayList<Vector> v0 = new ArrayList<>();
                Vector vect0 = new Vector();
                vect0.add(1.);
                vect0.add(0.);
                vect0.add(0.);
                vect0.add(0.);
                vect0.add(0.);
                vect0.add(0.);
                v0.add(vect0);
                recording(v0);
            }else if(text.equals("right")){
                Parser.parse();
                addCommand.setText("Recording right");
                ArrayList<Vector> v2 = new ArrayList<>();
                Vector vect2 = new Vector();
                vect2.add(1.);
                vect2.add(0.);
                vect2.add(0.);
                vect2.add(0.);
                vect2.add(0.);
                vect2.add(0.);
                v2.add(vect2);
                recording(v2);
            }else if(text.equals("up")){
                Parser.parse();
                addCommand.setText("Recording up");
                ArrayList<Vector> v1 = new ArrayList<>();
                Vector vect1 = new Vector();
                vect1.add(1.);
                vect1.add(0.);
                vect1.add(0.);
                vect1.add(0.);
                vect1.add(0.);
                vect1.add(0.);
                v1.add(vect1);
                recording(v1);
            }else{
                Parser.parse();
                addCommand.setText("Recording down");
                ArrayList<Vector> v3 = new ArrayList<>();
                Vector vect3 = new Vector();
                vect3.add(1.);
                vect3.add(0.);
                vect3.add(0.);
                vect3.add(0.);
                vect3.add(0.);
                vect3.add(0.);
                v3.add(vect3);
                recording(v3);
            }
        }
    };
    ArrayList<Vector> inputList = new ArrayList<>();
    NeuralNet net = new NeuralNet();
    public void recording(ArrayList<Vector> expected){
        net = ReadNet.getNet("database");
        System.out.println("a");
        String file = "database";
        Vector input = new Vector();
        int iterations = 2;
        MuseOSCServer museOSCServer = new MuseOSCServer();
        museOSCServer.museServer = new OscP5(museOSCServer,5000);
        GaussianDistribution g = new GaussianDistribution( 1750, 0, 10);
        //the bins
        final int[] count = {0};
        g.calculateIndices();
        OscEventListener o = new OscEventListener() {

            @Override
            public void oscEvent(OscMessage oscMessage) {
                if (oscMessage.checkAddrPattern("/muse/eeg")) {
                    Vector input = new Vector();
                    for (int i = 0; i < 4; ++i) {
                        double[] fourBuffer = new double[4];
                        boolean[] values = g.testIndices(fourBuffer);
                        for(int j = 0; j < values.length; ++j){
                            if(values[j]){
                                input.add(1.0);
                            }else{
                                input.add(0.0);
                            }
                        count[0]++;
                        }
                        float d = oscMessage.get(i).floatValue();
                        input.add((double)d);
                    }
                    inputList.add(input);

                    File f = new File("outputTest");//What i think this will do is overwrite it but I'm not sure
                    for(int i = 0; i < iterations; i++) {
                        net.update(inputList, expected);
                    }
                    inputList.clear();
                    input.clear();
                    expected.clear();
                    count[0]++;
                    System.out.println("a");

                    FileOutputStream fos = null;
                    ObjectOutputStream out = null;
                    System.out.println("about to write");
                    try {
                        fos = new FileOutputStream(file ,false);
                        out = new ObjectOutputStream(fos);
                        out.writeObject(net);
                        out.close();
                        System.out.println("Object Persisted");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }

            @Override
            public void oscStatus(OscStatus oscStatus) {

            }
        };
        museOSCServer.museServer.addListener(o);
    }
    public static void main(String args[]){
        SaveNet sn = new SaveNet();

    }
}