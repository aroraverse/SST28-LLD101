public class DriverAllocator implements DriverAllocationService {
    @Override
    public String allocateDriver(String studentId) {
        // fake deterministic driver
        return "DRV-17";
    }
}
