package Neuron;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.File;

public class Toolbelt {
    public static double quadrat(double x){
        return x * x;
    }

    // Standart Step-Function
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

    // Ausgelagertes Schreiben in eine Datei ~/Data
    public static void writeFile(List<String> lines, String filename) {
        try {
            File dir = new File("Data");
            if (!dir.exists()) {
                dir.mkdir();
            }

            try (FileWriter writer = new FileWriter(new File(dir, filename))) {
                for (String line : lines) {
                    writer.write(line + System.lineSeparator());
                }
            }
            System.out.println("Ausgabe wurde nach " + filename + " geschrieben.");

        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben der Datei: " + e.getMessage());
        }
    }

        public static double[] gewichteAnpassen(double Output, double desired, double[] w, double[] input){

        double[] neu = new double[w.length];
        for (int i = 0; i < neu.length; i++) {
            neu[i] = w[i] - (Output - desired) * input[i];

        }
        return neu;
    }
}
