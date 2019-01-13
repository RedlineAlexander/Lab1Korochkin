package SocketExample2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient1 implements Runnable {
    MyClient1() {

    }

    public void run() {
        System.out.println("MyClientl started");
        Socket clSockl = null;
        InputStream is = null;
        OutputStream osll = null;
        try {
            InetAddress IA;
            IA = InetAddress.getLocalHost();
            clSockl = new Socket(IA, 77);
            is = clSockl.getInputStream();
            int HI = is.read();
//2
            int[][] zMB = new int[HI][HI];
            int[][] zMC = new int[HI][HI];
            int[][] zMD = new int[HI][HI];
            int[][] zMM = new int[HI][HI];
            int[][] zMA = new int[HI][HI];
            int[][] zMP = new int[HI][HI];
            int[][] zMT = new int[HI][HI];
            int zs;
// отримання даних
            for (int i = 0; i < HI; i++) {
                for (int j = 0; j < HI; j++) {


                    zMB[i][j] = is.read();
                    zMC[i][j] = is.read();
                    zMD[i][j] = is.read();
                    // zMM[i][j]= is.read();
                }
            }
            //   zs = is.read();
// обчислення


            for (int i = 0; i < HI; i++) {
                for (int j = 0; j < HI; j++) {
                    for (int k = 0; k < HI; k++) {

                        zMP[i][j] += zMB[i][k] * zMC[k][j];
                        zMT[i][j] += zMD[i][k] * zMP[k][j];
                    }

                    //   zMA[i][j] = zMP[i][j] + zMT[i][j];
                }

            }
            /*
            for(int i=0;i<HI;i++){
                for(int j = 0 ; j < HI; j++)
                    zMX[i][j] += zMB[i][j] * zMC[i][j] + zMD[i][j] * zMM[i][j] * zs;
            }
            */
            osll = clSockl.getOutputStream();
// повернення результату
            for (int i = 0; i < HI; i++) {
                for (int j = 0; j < HI; j++) {
                    osll.write(zMT[i][j]);
                }
            }
            System.out.println("Clientl Finished ");
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }
}
