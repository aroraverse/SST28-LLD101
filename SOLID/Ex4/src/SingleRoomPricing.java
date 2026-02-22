public class SingleRoomPricing implements RoomPricingStrategy {
    @Override
    public Money getMonthlyPrice() {
        return new Money(14000.0);
    }
}
