package data_processing;

/**
 * Created by Aaron on 4/2/2016.
 */
import netP5.NetAddress;
import oscP5.*;

import java.util.Objects;

public class MuseOSCServer {


    static MuseOSCServer museOSCServer;
    public OscP5 museServer;
    static int recvPort = 5000;
    public MuseOSCServer(){

    }

    public static void main(String[] args) {
        museOSCServer = new MuseOSCServer();
        museOSCServer.museServer = new OscP5(museOSCServer,recvPort);
        museOSCServer.museServer.addListener(new OscEventListener() {

            @Override
            public void oscEvent(OscMessage oscMessage) {
                if(oscMessage.checkAddrPattern("/muse/eeg")){
                    float[] input = new float[4];
                    for(int i = 0; i < 4; ++i){
                        float d = oscMessage.get(i).floatValue();
                        input[i] = d;
                        System.out.println(d);
                    }
                }

            }

            @Override
            public void oscStatus(OscStatus oscStatus) {

            }
        });
        NetAddress netAddress = new NetAddress("127.0.0.1", 5000);
        //museOSCServer.museServer.connect(netAddress, null, null);
    }


}
