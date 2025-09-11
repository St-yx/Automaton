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

            double[] zielvektor = operation.getZielvektor();
            double[] w = lernen(zielvektor);
            
            double[][] binaer = {
                {0.0,0.0,1.0},
                {0.0,1.0,1.0},
                {1.0,0.0,1.0},
                {1.0,1.0,1.0},
            };

            for (double[] input : binaer) {
                System.out.printf("%.1f %s %.1f ist %.1f%n", input[0],operation.name(), input[1], Perceptron(input, w));
            }
        }           
    }      

    public static double[] lernen(double[] lernziel){
        double gesamtfehler = 1.0;
        double[] neueGewichte = new double[]{0.0,0.0,0.0};

        while (gesamtfehler != 0.0) {

            double[] input;
            double Output;
            gesamtfehler = 0.0;

            input = new double[]{0.0,0.0,1.0};
            Output = Perceptron(input, neueGewichte);
            neueGewichte = gewichteAnpassen(Output, lernziel[0], neueGewichte,input);
            gesamtfehler += Toolbelt.quadrat(Output - lernziel[0]);

            input = new double[]{0.0,1.0,1.0};
            Output = Perceptron(input, neueGewichte);
            neueGewichte = gewichteAnpassen(Output, lernziel[1], neueGewichte,input);
            gesamtfehler += Toolbelt.quadrat(Output - lernziel[1]);

            input = new double[]{1.0,0.0,1.0};
            Output = Perceptron(input, neueGewichte);
            neueGewichte = gewichteAnpassen(Output, lernziel[2], neueGewichte,input);
            gesamtfehler += Toolbelt.quadrat(Output - lernziel[2]);

            input = new double[]{1.0,1.0,1.0};
            Output = Perceptron(input, neueGewichte);
            neueGewichte = gewichteAnpassen(Output, lernziel[3], neueGewichte,input);
            gesamtfehler += Toolbelt.quadrat(Output - lernziel[3]);

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