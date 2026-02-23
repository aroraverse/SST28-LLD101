import java.util.Random;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) { this.repo = repo; }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        RoomPricingStrategy roomPricing = PricingFactory.getRoomPricing(req.roomType);
        Money basePrice = roomPricing.getMonthlyPrice();
        
        Money totalAddOns = new Money(0.0);
        for (AddOn addOn : req.addOns) {
            AddOnPricing pricing = PricingFactory.getAddOnPricing(addOn);
            if (pricing != null) {
                totalAddOns = totalAddOns.plus(pricing.getPrice());
            }
        }
        
        return basePrice.plus(totalAddOns);
    }
}
