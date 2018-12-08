package pl.edu.wat.softwarereliability;

import java.util.stream.IntStream;

class SchickWolvertonModel extends ReliabilityModel {

    static ReliabilityModel create(ModelInput modelInput) {
        SchickWolvertonModel schickWolvertonModel = new SchickWolvertonModel(modelInput);
        schickWolvertonModel.calculate();
        return schickWolvertonModel;
    }

    @Override
    protected void calculate() {
        calculateParameters();
        this.ET = Math.sqrt(Math.PI / (2 * fi * (N - SIZE)));
    }

    private SchickWolvertonModel(ModelInput modelInput) {
        super(modelInput);
    }

    private void calculateParameters() {
        int N = SIZE;
        double T = calculateT();
        double diff;
        do {
            N += 1;
            double left = calculateFirst(N, T);
            double right = calculateSecond(N, T);
            diff = Math.abs(left - right);
        } while (diff > epsilon);

        this.N = N;
        this.fi = (calculateFirst(N, T) + calculateSecond(N, T)) / 2;
    }

    private double calculateFirst(int N, double T) {
        double sum = 0;
        for (int i = 1; i <= SIZE; i++) {
            sum += 1d / ((N - (i - 1)) * T);
        }
        return 2 * sum;
    }

    private double calculateSecond(int N, double T) {
        double sum = 0;
        for (int i = 1; i <= SIZE; i++) {
            sum += (i - 1) * Math.pow(times.get(i - 1), 2);
        }
        return (2 * SIZE) / (N * T - sum);
    }

    private double calculateT() {
        return IntStream.range(0, SIZE)
                .mapToDouble(i -> Math.pow(times.get(i), 2))
                .sum();
    }
}
