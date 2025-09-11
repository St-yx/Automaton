package Neuron;

public class Toolbelt {
    public static double quadrat(double x){
        return x * x;
    }

    public static double StepFn(double x){
        return(x < 0.0)?0.0:1.0;
    }

    public static double Skalarprodukt(double[] v1, double[] v2){
        double sum = 0.0;
        for (int i = 0; i < Math.min(v1.length, v2.length); i++) {
            sum += v1[i] * v2[i];
        }
        return sum;
    }
/* 
    public static double[][] binaer(int x){

    }
*/
}
