public class TransportBookingService {
    private final DistanceService distanceService;
    private final DriverAllocationService driverService;
    private final PaymentService paymentService;
    private final FareCalculationService fareCalculator;

    public TransportBookingService(
            DistanceService distanceService,
            DriverAllocationService driverService,
            PaymentService paymentService,
            FareCalculationService fareCalculator) {
        this.distanceService = distanceService;
        this.driverService = driverService;
        this.paymentService = paymentService;
        this.fareCalculator = fareCalculator;
    }

    public void book(TripRequest req) {
        double km = distanceService.calculateDistance(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = driverService.allocateDriver(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = fareCalculator.calculateFare(km);

        String txn = paymentService.processPayment(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
