package views;

import Neurons.NeuralNet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by qadirhaqq on 4/2/16.
 */
public class ReadNet2 {
    public static NeuralNet getNet(String fileName) {
        NeuralNet n = new NeuralNet();
        FileInputStream fos;
        try {
            fos = new FileInputStream(fileName);
            ObjectInputStream oos = new ObjectInputStream(fos);
            n = (NeuralNet) oos.readObject();
            fos.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return n;
    }
}
