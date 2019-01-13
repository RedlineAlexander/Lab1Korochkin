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
        InputStream is = null;
        OutputStream os22 = null;
        try {
            InetAddress IA;
            IA = InetAddress.getLocalHost();
            clSock2 = new Socket(IA, 78);
            is = clSock2.getInputStream();
            int H2 = is.read();

//2.	Счет МХ4h  = MB4h*MC+ MD4h*MM*s
            int[][] zzMX = new int[H2][H2];
            int[][] zzMB = new int[H2][H2];
            int[][] zzMC = new int[H2][H2];
            int[][] zzMD = new int[H2][H2];
            int[][] zzMM = new int[H2][H2];
            int[][] zzMP = new int[H2][H2];
            int[][] zzMT = new int[H2][H2];
            int zzs;
// отримання даних
            for (int i = 0; i < H2; i++) {
                for (int j = 0; j < H2; j++) {


                    zzMB[i][j] = is.read();
                    zzMC[i][j] = is.read();
                    zzMD[i][j] = is.read();
                    zzMM[i][j] = is.read();
                }
            }
            zzs = is.read();
// обчислення
            for (int i = 0; i < H2; i++) {
                for (int j = 0; j < H2; j++) {
                    for (int k = 0; k < H2; k++) {

                        zzMP[i][j] += zzMB[i][k] * zzMC[k][j];
                        zzMT[i][j] += zzMD[i][k] * zzMM[k][j] * zzs;
                    }

                    zzMX[i][j] = zzMP[i][j] + zzMT[i][j];
                }

            }

/*
            for(int i=0;i<H2;i++){
                for(int j = 0 ; j < H2; j++)
                    zzMX[i][j] += zzMB[i][j] * zzMC[i][j] + zzMD[i][j] * zzMM[i][j] * zzs;
            }
            */
            os22 = clSock2.getOutputStream();
// повернення результату
            for (int i = 0; i < H2; i++) {
                for (int j = 0; j < H2; j++) {
                    os22.write(zzMX[i][j]);
                }
            }
            System.out.println("Client2 Finished ");
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }
}// MyClient2

