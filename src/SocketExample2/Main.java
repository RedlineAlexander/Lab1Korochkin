package SocketExample2;

public class Main {
    public static void main(String[] args) {
        Data x = new Data();
        new Thread(new MyServer1(x)).start();
        new Thread(new MyServer2(x)).start();
        new Thread(new MyClient1()).start();
        new Thread(new MyClient2()).start();
    }
}
