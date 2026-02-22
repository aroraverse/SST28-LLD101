public class DoubleRoomPricing implements RoomPricingStrategy {
    @Override
    public Money getMonthlyPrice() {
        return new Money(15000.0);
    }
}
