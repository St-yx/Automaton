package Neuron;

public enum Operation {
    OR(new double[]{0.0,1.0,1.0,1.0}),
    NOR(new double[]{1.0,0.0,0.0,0.0}),
    AND(new double[]{0.0,0.0,0.0,1.0}),
    NAND(new double[]{1.0,1.0,1.0,0.0});

    private final double[] zielvektor;

    Operation(double[] zielvektor){
        this.zielvektor = zielvektor;
    }

    public double[] getZielvektor(){
        return zielvektor;
    }

    public static Operation fromString(String eingabe){
        try {
            return Operation.valueOf(eingabe.toUpperCase());
        } catch(IllegalArgumentException exception){
            return null;
        }
    }
}
