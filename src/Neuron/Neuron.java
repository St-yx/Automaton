package Neuron;

// Enum zum Generieren des Zielvektors pro Operation
public enum Neuron {
    OR, NOR, AND, NAND;

    public double[] computeZielvektor(int n) {
        int reihen = (int) Math.pow(2, n);
        double[] ziel = new double[reihen];

        double[][] binaer = BinGen.Generator(n);

        for (int i = 0; i < reihen; i++) {
                double[] input = new double[n];
                System.arraycopy(binaer[i], 0, input, 0, n);

                boolean result = false;

             switch (this) {
                case OR:
                    result = false;
                    for (double v : input) if (v == 1.0) { result = true; break; }
                    break;
                case NOR:
                    result = true;
                    for (double v : input) if (v == 1.0) { result = false; break; }
                    break;
                case AND:
                    result = true;
                    for (double v : input) if (v == 0.0) { result = false; break; }
                    break;
                case NAND:
                    result = false;
                    for (double v : input) if (v == 0.0) { result = true; break; }
                    if (!result) result = false;
                    break;
            }
            ziel[i] = result ? 1.0 : 0.0;
        }
        return ziel;
    }

        public static Neuron fromString(String eingabe){
        try {
            return Neuron.valueOf(eingabe.toUpperCase());
        } catch(IllegalArgumentException exception){
            return null;
        }
    }

    // Gewichtsvektor aus Lernvektor generieren
    public static double[] lernen(double[] lernziel, int wdh, double[][] binaer){
        double gesamtfehler = 1.0;
        double[] neueGewichte = new double[binaer[0].length];

        while (gesamtfehler != 0.0) {

            double[] input;
            double Output;
            gesamtfehler = 0.0;

            for (int i = 0; i < wdh; i++) { 
                input = binaer[i];
                Output = Perceptron(input, neueGewichte);
                neueGewichte = Toolbelt.gewichteAnpassen(Output, lernziel[i], neueGewichte,input);
                gesamtfehler += Toolbelt.quadrat(Output - lernziel[i]);
            }
        }
        return neueGewichte;

    }
    
    // Perzeptron-Funktion
    public static double Perceptron(double[] input, double[] weights){
        return Toolbelt.StepFn(Toolbelt.Skalarprodukt(input,weights));
    }

}
