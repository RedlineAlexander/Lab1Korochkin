import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer1 implements Runnable {
    Data dl;

    MyServer1(Data d) {
        dl = d;
    }

    public void run() {
        System.out.println("MySereverl started ");
        ServerSocket srvl = null;
        Socket serSockl = null;
        OutputStream osl = null;
        InputStream isl = null;
        try {
            srvl = new ServerSocket(77);
            serSockl = srvl.accept();
            osl = serSockl.getOutputStream();
            isl = serSockl.getInputStream();
            //ввод данных
            for (int i = 0; i < dl.H; i++) {
                for (int j = 0; j < dl.H; j++) {
                    dl.MB[i][j] = 2;
                }

            }


            for (int i = 0; i < dl.N; i++) {
                for (int j = 0; j < dl.N; j++) {
                    dl.MC[i][j] = 2;
                }
            }


            for (int i = 0; i < dl.H; i++) {
                for (int j = 0; j < dl.H; j++) {
                    dl.MD[i][j] = 2;
                }
            }

            for (int i = 0; i < dl.N; i++) {
                for (int j = 0; j < dl.N; j++) {
                    dl.MM[i][j] = 2;
                }
            }
            dl.s = 1;


// передавання даних першому клієнту
            osl.write(dl.H);
            osl.write(dl.s);
            for (int i = 0; i < dl.H; i++) {
                for (int j = 0; j < dl.H; j++) {
                    osl.write(dl.MB[i][j]);
                    osl.write(dl.MC[i][j]);
                    osl.write(dl.MD[i][j]);
                    osl.write(dl.MM[i][j]);


                }
            }
            for (int i = 0; i < dl.H; i++) {
                for (int j = 0; j < dl.H; j++) {
                    for (int k = 0; k < dl.H; k++) {

                        dl.MP[i][j] += dl.MB[i][k] * dl.MC[k][j];
                        dl.MT[i][j] += dl.MD[i][k] * dl.MM[k][j] * dl.s;
                    }

                    dl.MX[i][j] = dl.MP[i][j] + dl.MT[i][j];
                }

            }
            /*
            for(int i=0;i<dl.H;i++){
                for(int j=0; j<dl.H; j++) {
                dl.MX[i][j] += dl.MB[i][j] * dl.MC[i][j] + dl.MD[i][j] * dl.MM[i][j] * dl.s;

                }
                }
                */

// отримання результату від першого клієнта
            for (int i = 0; i < dl.H; i++)
                for (int j = 0; j < dl.H; j++)
                    dl.MX[i][j] = isl.read();
// виведення результату
            dl.Wait();
            for (int i = 0; i < dl.H; i++)
                for (int j = 0; j < dl.H; j++)
                    System.out.println(" MX[" + i + "][" + j + "] = " + dl.MX[i][j]);
            System.out.println("MyServerl Finished ");
        } catch (IOException e) {
        }
    }// MyServerl
// Потік Сервер для взаємодії з другим клієнтом

}
