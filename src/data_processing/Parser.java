package data_processing;

import java.io.*;

/**
 * Created by josh on 4/2/16.
 */
public class Parser {

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

    public static void main(String[] args) {
        parse();
    }
}
