package Neuron;

public class BinGen {
    public static double [][] Generator(int n){
        int zeilen = (int) Math.pow(2, n);
        int spalten = n + 1;
        double [][] binaer = new double[zeilen][spalten];

        for (int i = 0; i < zeilen; i++) {
            for (int j = 0; j < spalten; j++) {
                int bit = ((i >> (n - j - 1)) & 1);
                binaer[i][j] = (double) bit;
            }
        }
        return binaer;
    }

}
