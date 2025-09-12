package Neuron;

import java.util.Scanner;
import java.util.List;

public class Neuron {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)){
 
            // Auswahl der zu lernenden Operation
                Operation operation = null;
            while (operation == null) {
                System.out.print("Welche Operation soll gelernt werden? (OR, NOR, AND, NAND): ");
                String eingabe = scanner.nextLine().trim();
                operation = Operation.fromString(eingabe);

                if (operation == null) {
                    System.out.println("Bitte im korrekten Format eingeben: ");
                }
            }

            // Eingangsvariable n abfragen
            int n = 0; 
            while (n < 2) {
                System.out.print("Wie viele Eingabewerte soll die Logikformel haben? (mindestens 2): ");
                if (scanner.hasNextInt()) {
                    n = scanner.nextInt();
                    if (n < 2) {
                        System.out.println("Die Anzahl der Eingabewerte muss mindestens 2 sein.");
                    }
                } else {
                    System.out.println("Bitte eine gültige Zahl eingeben.");
                    scanner.next();
                }
            }

            // Lernzielvektor und Binärmatrix generieren
            double[] zielvektor = operation.computeZielvektor(n);
            double[][] binaer = BinGen.Generator(n);
            double[] w = lernen(zielvektor, binaer.length, binaer);

            // Tabelle für Konsole und Datei
            List<String> ausgabe = new java.util.ArrayList<>();
            for (int i = 0; i < binaer.length; i++) {
                double[] input = binaer[i];
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < input.length - 1; j++) {
                    sb.append((int)input[j]);
                    if (j < input.length - 2) {
                        sb.append(" ");
                    }
                }
                double output = Perceptron(input, w);
                String line = String.format("%s -> %s = %.1f", sb.toString(), operation.name(), output);
                System.out.println(line);
                ausgabe.add(line);
            }

            // Ausgabe in Datei
            System.out.print("Soll die Ausgabe in eine Datei geschrieben werden? (j/n): ");
            String antwort = scanner.next().trim().toLowerCase();
            if (antwort.equals("j") || antwort.equals("ja")) {
                Toolbelt.writeFile(ausgabe, "output.txt");
            } else {
                System.out.println("Die Ausgabe wurde nicht in eine Datei geschrieben.");
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