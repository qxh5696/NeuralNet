package data_processing;

/**
 * Created by Aaron on 4/2/2016.
 */
import oscP5.*;

public class MuseOSCServer {
    static MuseOSCServer museOSCServer;
    OscP5 museServer;
    static int recvPort = 5000;

    public static void main(String[] args) {
        museOSCServer = new MuseOSCServer();
        museOSCServer.museServer = new OscP5(museOSCServer,recvPort);
    }

    void oscEvent(OscMessage msg){
        System.out.println("### got a message "+ msg);
        if( msg.checkAddrPattern("/muse/eeg")==true){
            for(int i=0; i <4;i++){
                System.out.print("EEG on channel " + i + ": " + msg.get(i).floatValue() + "\n");
            }
        }
    }

}
