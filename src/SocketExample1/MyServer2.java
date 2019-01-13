package SocketExample1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer2 implements Runnable {
    Data d2;

    MyServer2(Data d) {
        d2 = d;
    }

    public void run() {
        System.out.println("MySerever2 started ");
        ServerSocket srv2 = null;
        Socket serSock2 = null;
        OutputStream os2 = null;
        InputStream is2 = null;
        try {
            srv2 = new ServerSocket(78);
            serSock2 = srv2.accept();

            is2 = serSock2.getInputStream();
            os2 = serSock2.getOutputStream();


            for (int i = 0; i < d2.H; i++) {
                for (int j = 0; j < d2.H; j++) {
                    d2.MB[i][j] = 2;
                }

            }


            for (int i = 0; i < d2.N; i++) {
                for (int j = 0; j < d2.N; j++) {
                    d2.MC[i][j] = 2;
                }
            }


            //  for(int i = 0; i< d2.H;i++){
            //  for(int j=0;j<d2.H;j++) {
            //  d2.MD[i][j] = 1;
            //  }
            // }

            //for(int i = 0; i< d2.N;i++) {
            //for (int j = 0; j < d2.N; j++) {
            //  d2.MM[i][j] = 1;
            //  }
            //  }
            //   d2.s = 1;
// передавання даних другому клієнту
            os2.write(d2.H);
            //   os2.write(d2.s);
            for (int i = d2.H; i < d2.N; i++) {
                for (int j = d2.H; j < d2.N; j++) {
                    os2.write(d2.MB[i][j]);
                    os2.write(d2.MC[i][j]);
                    //          os2.write(d2.MD [i][j]);
                    //    os2.write(d2.MM [i][j]);
                }
            }
            for (int i = 0; i < d2.H; i++) {
                for (int j = 0; j < d2.H; j++) {
                    for (int k = 0; k < d2.H; k++) {

                        d2.MP[i][j] += d2.MB[i][k] * d2.MC[k][j];
                        //d2.MT [i][j] += d2.MD[i][k] * d2.MM[k][j] * d2.s;
                    }

                    //    d2.MX[i][j] = d2.MP[i][j] + d2.MT[i][j];
                }

            }
/*
            for(int i=0;i<d2.H;i++){
                for(int j=0; j<d2.H; j++) {
                    d2.MX[i][j] += d2.MB[i][j] * d2.MC[i][j] + d2.MD[i][j] * d2.MM[i][j] * d2.s;

                }
            }
            */

// отримання результату від другого клієнта
            for (int i = d2.H; i < d2.N; i++)
                for (int j = d2.H; j < d2.N; j++)
                    d2.MP[i][j] = is2.read();
// синхронізація
            d2.Signal();
            System.out.println("MyServer2 Finished ");
        } catch (IOException e) {
        }
    }
}
