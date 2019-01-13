package SocketExample2;

public class Data {
    int N = 12; // Розмір векторів
    int Cl = 2; // Кількість клієнтів
    int H = N / Cl;
    // Описування масивів
    int[][] MB = new int[N][N];
    int[][] MA = new int[N][N];
    int[][] MC = new int[N][N];
    int[][] MD = new int[N][N];
    int[][] MM = new int[N][N];
    //int [][] MX = new int[N][N];
    int[][] MP = new int[N][N];
    int[][] MT = new int[N][N];
    // int s;

//2.	Счет МХ4h  = MB4h*MC+ MD4h*MM*s

    int FI = 1; // Прапор для синхронізації

    // Конструктор
    Data() {
        MB[0][0] = 1;
        MB[0][1] = 1;
        MB[1][0] = 1;
        MB[1][1] = 1;
        MC[0][0] = 1;
        MC[0][1] = 1;
        MC[1][0] = 1;
        MC[1][1] = 1;
        MD[0][0] = 1;
        MD[0][1] = 1;
        MD[1][0] = 1;
        MD[1][1] = 1;
        //  MM [0][0]=1; MM [0][1]=1;MM[1][0]=1; MM[1][1]=1;
        // s  = 1;


    }

    // Методи для синхронізації
    synchronized void Wait() {
        try {
            if (FI == 1) wait();
        } catch (Exception e) {
        }
    }

    synchronized void Signal() {
        try {
            notify();
            FI = FI - 1;
        } catch (Exception e) {
        }
    }

}
