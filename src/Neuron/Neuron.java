package Neuron;

import java.util.Scanner;

public class Neuron {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)){
 
                Operation operation = null;

            while (operation == null) {
                System.out.print("Welche Operation soll gelernt werden? (OR, NOR, AND, NAND): ");
                String eingabe = scanner.nextLine().trim();
                operation = Operation.fromString(eingabe);

                if (operation == null) {
                    System.out.println("Bitte im korrekten Format eingeben: ");
                }
            }

            int n = 4; // Anzahl Eingabewerte für Logikformel

            double[] zielvektor = operation.computeZielvektor(n);
            double[][] binaer = BinGen.Generator(n);
            double[] w = lernen(zielvektor, binaer.length, binaer);

            for (double[] input : binaer) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < input.length - 1; i++) {
                    sb.append(input[i]).append(" ");
                    if (i < input.length - 2) {
                        sb.append(" ");
                    }
                }
                double out = Perceptron(input, w);
                System.out.printf("%s -> %s = %.1f%n", sb.toString(), operation.name(), out);
            }
        }           
    }      

    public static double[] lernen(double[] lernziel, int wdh, double[][] binaer){
        double gesamtfehler = 1.0;
        double[] neueGewichte = new double[binaer[0].length]; // Initialisierung der Gewichte mit 0.0

        while (gesamtfehler != 0.0) {

            double[] input;
            double Output;
            gesamtfehler = 0.0;

            for (int i = 0; i < wdh; i++) { 
                input = binaer[i];
                Output = Perceptron(input, neueGewichte);
                neueGewichte = gewichteAnpassen(Output, lernziel[i], neueGewichte,input);
                gesamtfehler += Toolbelt.quadrat(Output - lernziel[i]);
            }
        }
        return neueGewichte;

    }

    public static double[] gewichteAnpassen(double Output, double desired, double[] w, double[] input){

        double[] neu = new double[w.length];
        for (int i = 0; i < neu.length; i++) {
            neu[i] = w[i] - (Output - desired) * input[i];

        }
        return neu;
    }
 
    public static double Perceptron(double[] input, double[] weights){
        return Toolbelt.StepFn(Toolbelt.Skalarprodukt(input,weights));
    }
}