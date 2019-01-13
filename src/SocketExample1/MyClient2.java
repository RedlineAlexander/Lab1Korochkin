package SocketExample1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient2 implements Runnable {
    public void run() {
        System.out.println("MyClient2 started");
        Socket clSock2 = null;
        InputStream is2 = null;
        OutputStream osl = null;
        try {
            InetAddress IA;
            IA = InetAddress.getLocalHost();
            clSock2 = new Socket(IA, 78);
            is2 = clSock2.getInputStream();
            int H2 = is2.read();
//2
            int[][] zMB = new int[H2][H2];
            int[][] zMC = new int[H2][H2];
            int[][] zMD = new int[H2][H2];
            int[][] zMM = new int[H2][H2];
            int[][] zMX = new int[H2][H2];
            int[][] zMP = new int[H2][H2];
            int[][] zMT = new int[H2][H2];
            int zs;
            // Main_2 is2 = new Main_2();
// отримання даних
            for (int i = 0; i < H2; i++) {
                for (int j = 0; j < H2; j++) {


                    zMB[i][j] = is2.read();
                    zMC[i][j] = is2.read();
                    //  zMD [i][j] = is2.read() ;
                    //  zMM[i][j]= is2.read();

                }
            }
            //  zs = is2.read();
// обчислення


            for (int i = 0; i < H2; i++) {
                for (int j = 0; j < H2; j++) {
                    for (int k = 0; k < H2; k++) {

                        zMP[i][j] += zMB[i][k] * zMC[k][j];
                        // zMT [i][j] += zMD[i][k] * zMM[k][j] * zs;
                    }

                    //   zMX[i][j] = zMP[i][j] + zMT[i][j];
                }

            }
            /*
            for(int i=0;i<HI;i++){
                for(int j = 0 ; j < HI; j++)
                    zMX[i][j] += zMB[i][j] * zMC[i][j] + zMD[i][j] * zMM[i][j] * zs;
            }
            */
            osl = clSock2.getOutputStream();
// повернення результату
            for (int i = 0; i < H2; i++) {
                for (int j = 0; j < H2; j++) {
                    osl.write(zMP[i][j]);
                }
            }
            System.out.println("Client2 Finished ");
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }
}
