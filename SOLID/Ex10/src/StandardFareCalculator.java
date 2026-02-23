public class StandardFareCalculator implements FareCalculationService {
    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_RATE = 6.6666666667;
    
    @Override
    public double calculateFare(double distanceKm) {
        double fare = BASE_FARE + distanceKm * PER_KM_RATE;
        return Math.round(fare * 100.0) / 100.0;
    }
}
