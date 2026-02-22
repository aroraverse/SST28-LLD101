public class TripleRoomPricing implements RoomPricingStrategy {
    @Override
    public Money getMonthlyPrice() {
        return new Money(12000.0);
    }
}
