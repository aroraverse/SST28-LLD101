public class PaymentGateway implements PaymentService {
    @Override
    public String processPayment(String studentId, double amount) {
        // fake deterministic txn
        return "TXN-9001";
    }
}
