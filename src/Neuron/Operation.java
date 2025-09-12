package Neuron;

public enum Operation {
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

        public static Operation fromString(String eingabe){
        try {
            return Operation.valueOf(eingabe.toUpperCase());
        } catch(IllegalArgumentException exception){
            return null;
        }
    }

}
