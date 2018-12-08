package pl.edu.wat.softwarereliability;

class JelinskiMorandaModel extends ReliabilityModel {

    static ReliabilityModel create(ModelInput modelInput) {
        JelinskiMorandaModel jelinskiMorandaModel = new JelinskiMorandaModel(modelInput);
        jelinskiMorandaModel.calculate();
        return jelinskiMorandaModel;
    }

    @Override
    protected void calculate() {
        this.N = calculateN();
        this.fi = calculateFi();
        this.ET = 1d / (fi * (N - SIZE));
    }

    private JelinskiMorandaModel(ModelInput modelInput) {
        super(modelInput);
    }

    private int calculateN() {
        int N = SIZE;
        double diff;
        do {
            N += 1;
            double left = calculateLeft(N);
            double right = calculateRight(N);
            diff = Math.abs(left - right);
        } while (diff > epsilon);
        return N;
    }

    private double calculateFi() {
        return SIZE / calculateLowerPart(N);
    }

    private double calculateLeft(int N) {
        double value = 0;
        for (int i = 1; i <= SIZE; i++) {
            value += 1d / (N - (i - 1));
        }
        return value;
    }

    private double calculateRight(int N) {
        return calculateUpperSum() / calculateLowerPart(N);
    }

    private double calculateUpperSum() {
        return sumTimes() * SIZE;
    }

    private double calculateLowerPart(int N) {
        double firstValue = sumTimes();

        double secondValue = 0;
        for (int i = 1; i <= SIZE; i++) {
            secondValue += (i - 1) * times.get(i - 1);

        }
        return (firstValue * N) - secondValue;
    }

    private double sumTimes() {
        double sum = 0;
        for (int i = 1; i <= SIZE; i++) {
            sum += times.get(i - 1);
        }
        return sum;
    }
}
