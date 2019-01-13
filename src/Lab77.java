public class Lab77 {


    public static void main(String[] args) {
        Data x = new Data();
        new Thread(new MyServer1(x)).start();
        new Thread(new MyServer2(x)).start();
        new Thread(new MyClient1()).start();
        new Thread(new MyClient2()).start();
    }
}// Lab77 ---------------------------------------

