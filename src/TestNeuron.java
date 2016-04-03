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


/**
 * Created by qadirhaqq on 4/2/16.
 */
public class TestNeuron {
    TestNeuron(){

        SaveNet sn = new SaveNet();

    }
    private class SaveNet extends JFrame{
        SaveNet() {
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
            private void handleKeyPress(int keyCode) {
                Parser.parse();
                switch (keyCode) {
                    case 37://LEFT KEY
                        Parser.parse();
                        addCommand.setText("Recording left");
                        break;
                    case 38://UP KEY
                        Parser.parse();
                        addCommand.setText("Recording up");
                        break;
                    case 39://RIGHT KEY
                        Parser.parse();
                        addCommand.setText("Recording right");
                        break;
                    case 40://DOWN KEY
                        Parser.parse();
                        addCommand.setText("Recording down");
                        break;
                    default:
                        Parser.parse();
                }
            }
            public void actionPerformed(ActionEvent e) {
                processKeys();
            }

            private void processKeys(){
                KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
                    new KeyEventDispatcher()  {
                        public boolean dispatchKeyEvent(KeyEvent e){
                            if(e.getID() == KeyEvent.KEY_PRESSED){
                                handleKeyPress(e.getKeyCode());
                            }
                            return false;
                        }
                    });
            }
        };
    }





    private static class Parser {

        public static double[] parseLine(String s){
            String[] items = s.replace(" ","").split(",");
            if(items[1].equals("/muse/eeg")){
                double[] list = {Double.parseDouble(items[2]),Double.parseDouble(items[3]),Double.parseDouble(items[4]),Double.parseDouble(items[5])};
                return list;
            }
            return null;
        }

        public static void parse(){
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("recording.csv")));
                String s;
                while ( (s=bufferedReader.readLine()) != null){
                    double[] list = parseLine(s);
                    /*if (list!= null){
                        for (double d: list){
                            System.out.printf("%15.5f ", d);
                        }
                        System.out.printf("\n");
                    }*/
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
        public static void main(String[] args) {
            parse();

        }*/
    }


    private static class MuseOSCServer {
        static MuseOSCServer museOSCServer;

        OscP5 museServer;
        static int recvPort = 5000;

        void oscEvent(OscMessage msg){
            System.out.println("### got a message "+ msg);
            if( msg.checkAddrPattern("/muse/eeg")==true){
                for(int i=0; i <4;i++){
                    System.out.print("EEG on channel " + i + ": " + msg.get(i).floatValue() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {

        //TestNeuron tn = new TestNeuron();

        //double threshold = 1;
        NeuralNet net = new NeuralNet();
        net.addNeurons(40,23,6);

        Vector input = new Vector();
        ArrayList<Vector> inputs = new ArrayList<>();
        ArrayList<Vector> expected = new ArrayList<>();

        /*
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

        input.add(1.0);//weather
        input.add(1.0);//wife
        input.add(1.0);//transportation




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
        */

        /////////////MUSE OSC SERVER////////////////////

        MuseOSCServer museOSCServer;
        int recvPort = 5000;
        museOSCServer = new MuseOSCServer();
        museOSCServer.museServer = new OscP5(museOSCServer,recvPort);


        int iterations = 10000;

        //double highBound, double lowBound, int numOfIndices
        GaussianDistribution g = new GaussianDistribution( 1200, 0, 60);
        g.calculateIndices();
        ArrayList<Double> indicies = new ArrayList<>();
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
            //out.add(0.0);
            //out.add(1.0);
            //out.add(0.0);
            //out.add(1.0);
            //out.add(0.0);
            //out.add(1.0);
            expected.add(out.copy());
            out.clear();
        }
        //TODO Must quantify data into input vectors!
        for(int i = 0; i < 40; i++) {
            input.add(0.0);
            input.add(1.0);
            input.add(0.0);
        }
        System.out.println("Result 1 (Neural Net) before " + iterations + " iterations: "
                + net.calculate(input).get(0));
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




//      SaveNet sn = new SaveNet();
        System.out.println("Result 2 (Neural Net) after " + iterations + " iterations: " + net.calculate(input).get(0));
    }
}
