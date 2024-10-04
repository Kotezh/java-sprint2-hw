public class Converter {
    private static final int STEP_LENGTH = 75;
    private static final int STEP_CALORIES = 50;
    private static final int KILOCALORIE = 1000;
    private static final int KILOMETRE = 100000;

    int convertToKm(int steps) {
        return steps * STEP_LENGTH / KILOMETRE;
    }

    int convertStepsToKilocalories(int steps) {
        return steps * STEP_CALORIES / KILOCALORIE;
    }
}
