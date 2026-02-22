public class DeluxeRoomPricing implements RoomPricingStrategy {
    @Override
    public Money getMonthlyPrice() {
        return new Money(16000.0);
    }
}
